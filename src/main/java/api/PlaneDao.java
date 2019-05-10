

package api;

import entity.Plane;

import java.io.Serializable;
import java.util.List;

public interface PlaneDao extends Serializable {

        void savePlane(Plane plane);

        void removePlaneById(Long id);

        Plane getPlaneById(Long id);

        List<Plane> getPlaneByModelNumber(Integer modelNumber);

        List<Plane> getAllPlane();

        List<Plane> getPlaneByCapacity(Integer capacity);

        void updatePlane(Plane plane);
}
