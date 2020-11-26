package com.practice.yeonda_makerthon;

public class BasicReservationClass {
    private String capacity,date, doorlock_id, end_time, price_option_id,
            reservation_id, space_id,start_time, user;
    private int status;

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDoorlock_id() {
        return doorlock_id;
    }

    public void setDoorlock_id(String doorlock_id) {
        this.doorlock_id = doorlock_id;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getPrice_option_id() {
        return price_option_id;
    }

    public void setPrice_option_id(String price_option_id) {
        this.price_option_id = price_option_id;
    }

    public String getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(String reservation_id) {
        this.reservation_id = reservation_id;
    }

    public String getSpace_id() {
        return space_id;
    }

    public void setSpace_id(String space_id) {
        this.space_id = space_id;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public BasicReservationClass() {
    }
}
