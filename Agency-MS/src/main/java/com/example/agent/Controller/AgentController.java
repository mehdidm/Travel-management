package com.example.agent.Controller;

import com.example.agent.Model.Agent;
import com.example.agent.Services.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/agent", produces = MediaType.APPLICATION_JSON_VALUE)
public class AgentController {
    @Autowired
    private AgentService service;

    @PostMapping("/add")
    public ResponseEntity<Object> addAgent(@RequestBody Agent agent) {
        Agent savedAgent = service.saveAgent(agent);
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("data", savedAgent);
        responseBody.put("message", "Agent created successfully");
        responseBody.put("status",201);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

    @PostMapping("/add-list")
    public ResponseEntity<Object> addAgents(@RequestBody List<Agent> agents){
        List<Agent> agentsCreated=service.saveAgents(agents);

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("data", agentsCreated);
        responseBody.put("message", "Agents created successfully");
        responseBody.put("status",201);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

    @GetMapping("/get-list")
    public ResponseEntity<Object> findAllAgents(){
        List<Agent> agents=service.getAgents();

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("data", agents);
        responseBody.put("message", "Operation completed successfully");
        responseBody.put("status",200);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseBody);
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<Object> findAgentById(@PathVariable int id){
        Object agent=service.getAgentById(id);

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("data", agent);
        responseBody.put("message", "Operation completed successfully");
        responseBody.put("status",200);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseBody);
    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<Object> findAgentByName(@PathVariable String name){
        Agent agent=service.getAgentByName(name);

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("data", agent);
        responseBody.put("message", "Operation completed successfully");
        responseBody.put("status",200);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseBody);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteAgent(@PathVariable int id){
        String msg=service.deleteAgent(id);

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("status",200);
        responseBody.put("message", msg);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseBody);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateAgent(@RequestBody Agent agent,@PathVariable int id){
        Agent agentUpdated=service.updateAgent(agent,id);

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("status",200);
        responseBody.put("message", "Operation completed successfully");
        responseBody.put("data", agentUpdated);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseBody);
    }

    public static interface GreetingController {
        @RequestMapping("/greeting")
        String greeting();
    }
}
