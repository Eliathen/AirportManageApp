

package api;

import entity.Plane;

import java.util.List;

public interface PlaneDao {

        void saveEmplyee(Plane plane);

        void removePlaneById(Long id);

        List<Plane> getPlaneByModelNumber(Integer modelNumner);

        List<Plane> getAllPlane();

        List<Plane> getPlaneByCapacity(Integer capacity);

        void updateFlight(Plane plane);
}
