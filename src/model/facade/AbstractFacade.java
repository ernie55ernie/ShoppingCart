package model.facade;

import java.util.List;

/**
 *
 * @author Ernie
 */
public abstract class AbstractFacade<T>{
	private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

	// protected abstract EntityManager getEntityManager();
    protected abstract List<T> getList();
    
	public void create(T entity){
		/*getEntityManager().persist(entity);*/
	}

	public void edit(T entity){
		/*getEntityManager().merge(entity);*/
	}

	public void remove(T entity){
		/*getEntityManager().remove(getEntityManager().merge(entity));*/
	}

	public T find(Object id){
		/*return getEntityManager().find(entityClass, id);*/
		return null;
	}

	public List<T> findAll(){
		/*CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		return getEntityManager().createQuery(cq).getResultList();*/
		return null;
	}

	public List<T> findRange(int[] range){
		/*CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		Query q = getEntityManager().createQuery(cq);
		q.setMaxResults(range[1] - range[0]);
		q.setFirstResult(range[0]);
		return q.getResultList();*/
		return null;
	}

	public int count(){
		/*CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
		Root<T> rt = cq.from(entityClass);
		cq.select(getEntityManager().getCriteriaBuilder().count(rt));
		Query q = getEntityManager().createQuery(cq);
		return ((Long) q.getSingleResult()).intValue();*/
		return getList().size();
	}
}