package com.medication.app.controller;

import com.medication.app.dto.ActivityDTO;
import com.medication.app.service.hospital.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    @GetMapping("/allActivities")
    public ResponseEntity<List<ActivityDTO>> getActivities() {
        List<ActivityDTO> dtos = activityService.findActivities();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping(value = "/findActivityById/{id}")
    public ResponseEntity<ActivityDTO> getActivity(@PathVariable("id") Long id) {
        ActivityDTO dto = activityService.findActivityById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(value = "/findActivityByName/{name}")
    public ResponseEntity<ActivityDTO> getActivityByName(@PathVariable("name") String name) {
        ActivityDTO dto = activityService.findActivityByName(name);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteActivityById/{id}")
    public ResponseEntity<ActivityDTO> deleteActivityById(@PathVariable("id") Long id) {
        activityService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/deleteActivityByName/{name}")
    public ResponseEntity<ActivityDTO> deleteActivityByName(@PathVariable("name") String name) {
        activityService.deleteByName(name);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/insertActivity")
    public ResponseEntity<ActivityDTO> insertActivity(@RequestBody ActivityDTO activityDTO){

        activityService.insert(activityDTO);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
