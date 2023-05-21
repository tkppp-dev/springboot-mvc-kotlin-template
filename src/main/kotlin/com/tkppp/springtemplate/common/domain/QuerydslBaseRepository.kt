package com.tkppp.springtemplate.common.domain

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

class QuerydslBaseRepository(domainClass: Class<*>) : QuerydslRepositorySupport(domainClass) {

    @PersistenceContext
    override fun setEntityManager(entityManager: EntityManager) {
        super.setEntityManager(entityManager)
    }
}