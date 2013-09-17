/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityDAO;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entity.Customer;
import Entity.Savings;
import EntityDAO.exceptions.NonexistentEntityException;
import EntityDAO.exceptions.PreexistingEntityException;
import EntityDAO.exceptions.RollbackFailureException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Vextroid
 */
public class SavingsJpaController implements Serializable {

    public SavingsJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Savings savings) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Customer CId = savings.getCId();
            if (CId != null) {
                CId = em.getReference(CId.getClass(), CId.getCId());
                savings.setCId(CId);
            }
            em.persist(savings);
            if (CId != null) {
                CId.getSavingsCollection().add(savings);
                CId = em.merge(CId);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findSavings(savings.getAccNum()) != null) {
                throw new PreexistingEntityException("Savings " + savings + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Savings savings) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Savings persistentSavings = em.find(Savings.class, savings.getAccNum());
            Customer CIdOld = persistentSavings.getCId();
            Customer CIdNew = savings.getCId();
            if (CIdNew != null) {
                CIdNew = em.getReference(CIdNew.getClass(), CIdNew.getCId());
                savings.setCId(CIdNew);
            }
            savings = em.merge(savings);
            if (CIdOld != null && !CIdOld.equals(CIdNew)) {
                CIdOld.getSavingsCollection().remove(savings);
                CIdOld = em.merge(CIdOld);
            }
            if (CIdNew != null && !CIdNew.equals(CIdOld)) {
                CIdNew.getSavingsCollection().add(savings);
                CIdNew = em.merge(CIdNew);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = savings.getAccNum();
                if (findSavings(id) == null) {
                    throw new NonexistentEntityException("The savings with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Savings savings;
            try {
                savings = em.getReference(Savings.class, id);
                savings.getAccNum();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The savings with id " + id + " no longer exists.", enfe);
            }
            Customer CId = savings.getCId();
            if (CId != null) {
                CId.getSavingsCollection().remove(savings);
                CId = em.merge(CId);
            }
            em.remove(savings);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Savings> findSavingsEntities() {
        return findSavingsEntities(true, -1, -1);
    }

    public List<Savings> findSavingsEntities(int maxResults, int firstResult) {
        return findSavingsEntities(false, maxResults, firstResult);
    }

    private List<Savings> findSavingsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Savings.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Savings findSavings(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Savings.class, id);
        } finally {
            em.close();
        }
    }

    public int getSavingsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Savings> rt = cq.from(Savings.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
