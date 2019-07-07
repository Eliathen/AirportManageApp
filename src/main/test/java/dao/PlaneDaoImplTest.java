package dao;

import entity.Plane;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PlaneDaoImplTest {

    public static PlaneDaoImpl planeDao;

    @BeforeAll
    public static void init(){
        assertDoesNotThrow(()->{
            planeDao  = new PlaneDaoImpl();
            planeDao.openCurrentSessionWithTransaction();
        });
    }
    @AfterAll
    public static void close(){
        planeDao.getCurrentTransaction().rollback();
    }

    @Test
    @Order(0)
    void savePlane() {
        Plane plane = new Plane("reg1", 123, "plane1", 70, 1000 );
        planeDao.savePlane(plane);
        assertEquals(plane.getCapacity(), planeDao.getPlaneById(1L).getCapacity());
    }

    @Test
    @Order(3)
    void removePlaneById() {
        planeDao.removePlaneById(1L);
        assertNull(planeDao.getPlaneById(1L));
    }

    @Test
    @Order(1)
    void getPlaneById() {
        assertEquals(123, planeDao.getPlaneById(1L).getModelNumber());
    }

    @Test
    @Order(2)
    void updatePlane() {
        Plane plane = planeDao.getPlaneById(1L);
        plane.setCapacity(100);
        planeDao.updatePlane(plane);
        assertEquals(plane.getCapacity(), planeDao.getPlaneById(1L).getCapacity());
    }

}