package dao;

import api.PlaneDao;
import entity.Plane;
import org.hibernate.query.Query;

import java.util.LinkedList;
import java.util.List;

public class PlaneDaoImpl extends BaseDao implements PlaneDao {

    public PlaneDaoImpl() {
    }

    public void savePlane(Plane plane){
        if(!isPlaneAlreadyExist(plane.getId())){
            getCurrentSession().save(plane);
        }else{

        }
    }

    public void removePlaneById(Long id){
        Plane plane = getPlaneById(id);
        getCurrentSession().delete(plane);
    }
    private boolean isPlaneAlreadyExist(Long id){
        try {
            if (getPlaneById(id).getId() != null) {
                return true;
            }
            return false;
        }catch(NullPointerException e){
            return false;
        }
    }
    public Plane getPlaneByIdWithFlight(Plane plane){
        try{
            Query query = getCurrentSession().createQuery("FROM Flight where planeid =: planeid");
            query.setParameter("planeid", plane.getId());
            plane.setFlights(query.getResultList());
            return plane;
        }catch(NullPointerException e){
            return plane;
        }
    }
    public Plane getPlaneById(Long id){
        try{
            Query query = getCurrentSession().createQuery("FROM Plane where id =: id");
            query.setParameter("id", id);
            Plane plane = (Plane)query.uniqueResult();
            return plane;
        }catch(NullPointerException e){
            return new Plane();
        }
    }
    public List<Plane> getPlaneByModelNumber(Integer modelNumber){
        try{
            Query query = getCurrentSession().createQuery("FROM Plane where modelNumber =: modelNumber");
            query.setParameter("modelNumber", modelNumber);
            List <Plane> planes = query.getResultList();
            return planes;
        }catch(NullPointerException e){
            return new LinkedList<Plane>();
        }
    }

    public List<Plane> getAllPlane(){
        try{
            Query query = getCurrentSession().createQuery("FROM Plane");
            List <Plane> planes = query.getResultList();
            return planes;
        }catch(NullPointerException e){
            return new LinkedList<Plane>();
        }
    }

    public List<Plane> getPlaneByCapacity(Integer capacity){
        try{
            Query query = getCurrentSession().createQuery("FROM Plane where capacity >=: capacity");
            query.setParameter("capacity", capacity);
            List <Plane> planes = query.getResultList();
            return planes;
        }catch(NullPointerException e){
            return new LinkedList<Plane>();
        }
    }

    public void updatePlane(Plane plane){
        getCurrentSession().saveOrUpdate(plane);
    }
}
