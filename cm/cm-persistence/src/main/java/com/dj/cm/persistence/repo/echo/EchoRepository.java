package com.dj.cm.persistence.repo.echo;

import com.dj.cm.model.entity.Echo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EchoRepository extends CrudRepository<Echo, Long> {

}