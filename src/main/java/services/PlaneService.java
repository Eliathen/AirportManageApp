package services;

import api.PlaneDao;
import dao.PlaneDaoImpl;
import entity.Plane;
import exceptions.ApplicationException;

import java.util.List;

public class PlaneService {

    private PlaneDaoImpl planeDao;

    public PlaneService() {
        planeDao = new PlaneDaoImpl();
    }

    public void savePlane(Plane plane) throws ApplicationException {
        planeDao.openCurrentSessionWithTransaction();
        planeDao.savePlane(plane);
        planeDao.closeCurrentSessionWithTransaction();
    }

    public void removePlaneById(Long id) throws ApplicationException {
        planeDao.openCurrentSessionWithTransaction();
        planeDao.removePlaneById(id);
        planeDao.closeCurrentSessionWithTransaction();

    }

    public void updatePlane(Plane plane) throws ApplicationException {
        planeDao.openCurrentSessionWithTransaction();
        planeDao.updatePlane(plane);
        planeDao.closeCurrentSessionWithTransaction();
    }

}
