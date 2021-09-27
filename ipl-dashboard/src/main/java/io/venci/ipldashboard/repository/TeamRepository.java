package io.venci.ipldashboard.repository;

import org.springframework.data.repository.CrudRepository;

import io.venci.ipldashboard.model.Team;

public interface TeamRepository extends CrudRepository<Team, Long>{
	Team findByTeamName(String teamName);
}
