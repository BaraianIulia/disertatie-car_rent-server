package com.disertatie.rent.car.repository;

import com.disertatie.rent.car.model.CarModel;
import com.disertatie.rent.car.model.CarQuizzModel;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository(value = "repo")
public class Repo {

    private static final Logger LOG = LoggerFactory.getLogger(Repo.class);
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<CarModel> findAllFiltered(CarQuizzModel carQuizzModel) {
        List<CarModel> result = new ArrayList<>();

        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM cars AS c ");
//        if(carQuizzModel.getStartDate()!= null && carQuizzModel.getEndDate()!= null){
//            query.append("LEFT JOIN rent_details AS rd ON c.car_id = rd.car_id ");
//        }
        query.append("WHERE ");
//        if(carQuizzModel.getStartDate() != null && carQuizzModel.getEndDate() != null){
//            LocalDate startDate = carQuizzModel.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//            LocalDate endDate = carQuizzModel.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//            query.append("(rd.rent_detail_id is null or (('"+endDate +"' < rd.start_date and '"+startDate+"' < rd.start_date) AND (rd.end_date < '"+startDate+"' and rd.end_date < '"+endDate+"'))) ");
//            if(CollectionUtils.isNotEmpty(carQuizzModel.getBrand()) || carQuizzModel.getFabricationYear() != null || carQuizzModel.getPricePerDay() != null || carQuizzModel.getHorsePower() != null || carQuizzModel.getSeats() != null){
//                query.append("AND ");
//            }
//        }
        if (CollectionUtils.isNotEmpty(carQuizzModel.getBrand())) {
            String brandList = carQuizzModel.getBrand().stream().collect(Collectors.joining("','", "'", "'"));
            query.append("c.brand IN (" + brandList + ") ");
            if (carQuizzModel.getFabricationYear() != null || carQuizzModel.getPricePerDay() != null || carQuizzModel.getHorsePower() != null || carQuizzModel.getSeats() != null) {
                query.append("AND ");
            }
        }
        if (carQuizzModel.getFabricationYear() != null) {
            int minValue = carQuizzModel.getFabricationYear() - 3;
            int maxValue = carQuizzModel.getFabricationYear() + 3;
            query.append("c.fabrication_year >= '" + minValue + "' AND c.fabrication_year <= '" + maxValue + "' ");
            if (carQuizzModel.getPricePerDay() != null || carQuizzModel.getHorsePower() != null || carQuizzModel.getSeats() != null) {
                query.append("AND ");
            }
        }
        if (carQuizzModel.getPricePerDay() != null) {
            int minValue = carQuizzModel.getPricePerDay() - 5;
            int maxValue = carQuizzModel.getPricePerDay() + 5;
            query.append("c.price_per_day >= '" + minValue + "' AND c.price_per_day <= '" + maxValue + "' ");
            if (carQuizzModel.getHorsePower() != null || carQuizzModel.getSeats() != null) {
                query.append("AND ");
            }
        }
        if (carQuizzModel.getHorsePower() != null) {
            int minValue = carQuizzModel.getHorsePower() - 10;
            int maxValue = carQuizzModel.getHorsePower() + 10;
            query.append("c.horse_power >= '" + minValue + "' AND c.horse_power <= '" + maxValue + "' ");
            if (carQuizzModel.getSeats() != null) {
                query.append("AND ");
            }
        }
        if (carQuizzModel.getSeats() != null) {
            int minValue = carQuizzModel.getSeats();
            int maxValue = carQuizzModel.getSeats() + 2;
            query.append("c.seats >= '" + minValue + "' AND c.seats <= '" + maxValue + "' ");
        }

        LOG.error(query.toString());

//        SqlRowSet rowSet = (SqlRowSet) jdbcTemplate.queryForRowSet(query.toString());
//        while(rowSet.next()){
//            CarModel carModel = new CarModel();
//            carModel.setId(rowSet.getLong("car_id"));
//            carModel.setVehicleIdentificationNumber(rowSet.getString("vehicle_identification_number"));
//            carModel.setBrand(rowSet.getString("brand"));
//            carModel.setModel(rowSet.getString("model"));
//            carModel.setDoors(rowSet.getInt("doors"));
//            carModel.setSeats(rowSet.getInt("seats"));
//            carModel.setFabricationYear(rowSet.getInt("fabrication_year"));
//           carModel.setGearbox(rowSet.getString("gearbox"));
//           carModel.setPricePerDay(rowSet.getDouble("price_per_day"));
//           carModel.setInsurance(rowSet.getDouble("insurance"));
//           carModel.setHorsePower(rowSet.getDouble("horse_power"));
//           carModel.setHexColor(rowSet.getString("hex_color"));
//           carModel.setColor(rowSet.getString("color"));
//           carModel.setConditionalAir(rowSet.getBoolean("conditional_air"));
//           carModel.setFuelType(rowSet.getString("fuel_type"));
//            carModel.setLuggageCarrierVolume(rowSet.getInt("luggage_carrier_volume"));
//            carModel.setPhoto(rowSet.getb("photo"));
//            result.add(carModel);
//        }

        SqlRowSet rowSet = (SqlRowSet) jdbcTemplate.queryForRowSet(query.toString());
        while (rowSet.next()) {
            CarModel carModel = new CarModel();
            carModel.setId(rowSet.getLong("car_id"));
            carModel.setVehicleIdentificationNumber(rowSet.getString("vehicle_identification_number"));
            carModel.setBrand(rowSet.getString("brand"));
            carModel.setModel(rowSet.getString("model"));
            carModel.setDoors(rowSet.getInt("doors"));
            carModel.setSeats(rowSet.getInt("seats"));
            carModel.setFabricationYear(rowSet.getInt("fabrication_year"));
            carModel.setGearbox(rowSet.getString("gearbox"));
            carModel.setPricePerDay(rowSet.getDouble("price_per_day"));
            carModel.setInsurance(rowSet.getDouble("insurance"));
            carModel.setHorsePower(rowSet.getDouble("horse_power"));
            carModel.setHexColor(rowSet.getString("hex_color"));
            carModel.setColor(rowSet.getString("color"));
            carModel.setConditionalAir(rowSet.getBoolean("conditional_air"));
            carModel.setFuelType(rowSet.getString("fuel_type"));
            carModel.setLuggageCarrierVolume(rowSet.getInt("luggage_carrier_volume"));
            carModel.setPhoto(new String((byte[]) rowSet.getObject("photo"), StandardCharsets.UTF_8));
            result.add(carModel);
        }
        return result;
    }
}
