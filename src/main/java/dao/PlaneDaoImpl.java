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
    public void updatePlane(Plane plane){
        getCurrentSession().saveOrUpdate(plane);
    }
}
