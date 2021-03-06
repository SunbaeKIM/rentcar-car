package Encar;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name="Car")
public class Car {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long carId;
    private String carName;
    private Integer quantity;

    @PostUpdate
    public void pulishQuantityChecked(){
        QuantityChanged quantityChanged = new QuantityChanged();
        BeanUtils.copyProperties(this, quantityChanged);
        quantityChanged.publishAfterCommit();
    }

/*    @PrePersist
    public void onPrePersist(){
        QuantityChanged quantityChanged = new QuantityChanged();
        BeanUtils.copyProperties(this, quantityChanged);
        quantityChanged.publishAfterCommit();
    }*/

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }
    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }




}
