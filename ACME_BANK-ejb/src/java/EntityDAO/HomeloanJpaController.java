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
import Entity.Homeloan;
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
public class HomeloanJpaController implements Serializable {

    public HomeloanJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Homeloan homeloan) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Customer CId = homeloan.getCId();
            if (CId != null) {
                CId = em.getReference(CId.getClass(), CId.getCId());
                homeloan.setCId(CId);
            }
            em.persist(homeloan);
            if (CId != null) {
                CId.getHomeloanCollection().add(homeloan);
                CId = em.merge(CId);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findHomeloan(homeloan.getAccNum()) != null) {
                throw new PreexistingEntityException("Homeloan " + homeloan + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Homeloan homeloan) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Homeloan persistentHomeloan = em.find(Homeloan.class, homeloan.getAccNum());
            Customer CIdOld = persistentHomeloan.getCId();
            Customer CIdNew = homeloan.getCId();
            if (CIdNew != null) {
                CIdNew = em.getReference(CIdNew.getClass(), CIdNew.getCId());
                homeloan.setCId(CIdNew);
            }
            homeloan = em.merge(homeloan);
            if (CIdOld != null && !CIdOld.equals(CIdNew)) {
                CIdOld.getHomeloanCollection().remove(homeloan);
                CIdOld = em.merge(CIdOld);
            }
            if (CIdNew != null && !CIdNew.equals(CIdOld)) {
                CIdNew.getHomeloanCollection().add(homeloan);
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
                String id = homeloan.getAccNum();
                if (findHomeloan(id) == null) {
                    throw new NonexistentEntityException("The homeloan with id " + id + " no longer exists.");
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
            Homeloan homeloan;
            try {
                homeloan = em.getReference(Homeloan.class, id);
                homeloan.getAccNum();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The homeloan with id " + id + " no longer exists.", enfe);
            }
            Customer CId = homeloan.getCId();
            if (CId != null) {
                CId.getHomeloanCollection().remove(homeloan);
                CId = em.merge(CId);
            }
            em.remove(homeloan);
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

    public List<Homeloan> findHomeloanEntities() {
        return findHomeloanEntities(true, -1, -1);
    }

    public List<Homeloan> findHomeloanEntities(int maxResults, int firstResult) {
        return findHomeloanEntities(false, maxResults, firstResult);
    }

    private List<Homeloan> findHomeloanEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Homeloan.class));
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

    public Homeloan findHomeloan(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Homeloan.class, id);
        } finally {
            em.close();
        }
    }

    public int getHomeloanCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Homeloan> rt = cq.from(Homeloan.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
