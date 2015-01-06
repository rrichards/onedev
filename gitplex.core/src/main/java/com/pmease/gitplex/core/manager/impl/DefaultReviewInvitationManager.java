package com.pmease.gitplex.core.manager.impl;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.hibernate.criterion.Restrictions;

import com.pmease.commons.hibernate.Sessional;
import com.pmease.commons.hibernate.Transactional;
import com.pmease.commons.hibernate.dao.Dao;
import com.pmease.commons.hibernate.dao.EntityCriteria;
import com.pmease.gitplex.core.manager.NotificationManager;
import com.pmease.gitplex.core.manager.ReviewInvitationManager;
import com.pmease.gitplex.core.model.PullRequest;
import com.pmease.gitplex.core.model.ReviewInvitation;
import com.pmease.gitplex.core.model.User;

@Singleton
public class DefaultReviewInvitationManager implements ReviewInvitationManager {

	private final Dao dao;
	
	private final NotificationManager notificationManager;
	
	@Inject
	public DefaultReviewInvitationManager(Dao dao, NotificationManager notificationManager) {
		this.dao = dao;
		this.notificationManager = notificationManager;
	}

	@Sessional
	@Override
	public ReviewInvitation find(User reviewer, PullRequest request) {
		return dao.find(EntityCriteria.of(ReviewInvitation.class)
				.add(Restrictions.eq("reviewer", reviewer))
				.add(Restrictions.eq("request", request)));
	}

	@Transactional
	@Override
	public void save(ReviewInvitation invitation) {
		dao.persist(invitation);
		notificationManager.notifyReview(invitation);
	}

}
