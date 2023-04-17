package com.example.agent.Services;

import com.example.agent.Model.Agent;
import com.example.agent.Repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgentService {
    @Autowired
    private AgentRepository agentReopsitory;
    public Agent getAgentById(int id) {
        return agentReopsitory.findById(id).orElse(null);
    }

    public Agent getAgentByName(String name) {
        return agentReopsitory.findByName(name);
    }

    public List<Agent> getAgents(){
        return agentReopsitory.findAll();
    }

    public Agent saveAgent(Agent agent) {
        return agentReopsitory.save(agent);
    }

    public List<Agent> saveAgents(List<Agent> agents){
        return agentReopsitory.saveAll(agents);
    }

    public String deleteAgent(int id){
        agentReopsitory.deleteById(id);
        return "Agent removed !! "+id;
    }

    public Agent updateAgent(Agent agent,int id){
        Agent existingAgent=agentReopsitory.findById(id).orElse(null);

        existingAgent.setName(agent.getName());
        existingAgent.setDescription(agent.getDescription());
        existingAgent.setAddress(agent.getAddress());

        return agentReopsitory.save(existingAgent);
    }
}
