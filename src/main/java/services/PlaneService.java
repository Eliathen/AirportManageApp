package services;

import api.PlaneDao;
import dao.PlaneDaoImpl;
import entity.Plane;

import java.util.List;

public class PlaneService {
    private PlaneDaoImpl planeDao;

    public PlaneService() {
        planeDao = new PlaneDaoImpl();
    }
    public void savePlane(Plane plane) {
        planeDao.openCurrentSessionWithTransaction();
        planeDao.savePlane(plane);
        planeDao.closeCurrentSessionWithTransaction();
    }

    public void removePlaneById(Long id){
        planeDao.openCurrentSessionWithTransaction();
        planeDao.removePlaneById(id);
        planeDao.closeCurrentSessionWithTransaction();

    }
    public List<Plane> getPlaneByModelNumber(Integer modelNumber){
        planeDao.openCurrentSession();
        List<Plane> planes = planeDao.getPlaneByModelNumber(modelNumber);
        planeDao.closeCurrentSession();
        return planes;
    }
    public Plane getPlaneByIdWithFlight(Long id){
        planeDao.openCurrentSession();
        Plane plane = planeDao.getPlaneById(id);
        plane = planeDao.getPlaneByIdWithFlight(plane);
        planeDao.closeCurrentSession();
        return plane;
    }
    public Plane getPlaneById(Long id){
        planeDao.openCurrentSession();
        Plane plane = planeDao.getPlaneById(id);
        planeDao.closeCurrentSession();
        return plane;
    }

    public List<Plane> getAllPlane(){
        planeDao.openCurrentSession();
        List<Plane> planes = planeDao.getAllPlane();
        planeDao.closeCurrentSession();
        return planes;
    }

    public List<Plane> getPlaneByCapacity(Integer capacity){
        planeDao.openCurrentSession();
        List<Plane> planes = planeDao.getPlaneByCapacity(capacity);
        planeDao.closeCurrentSession();
        return planes;
    }

    public void updatePlane(Plane plane){
        planeDao.openCurrentSessionWithTransaction();
        planeDao.updatePlane(plane);
        planeDao.closeCurrentSessionWithTransaction();
    }

}
