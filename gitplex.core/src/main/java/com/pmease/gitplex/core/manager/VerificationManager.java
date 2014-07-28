package com.pmease.gitplex.core.manager;

import java.util.Collection;

import com.google.inject.ImplementedBy;
import com.pmease.gitplex.core.manager.impl.DefaultVerificationManager;
import com.pmease.gitplex.core.model.PullRequest;
import com.pmease.gitplex.core.model.Verification;

@ImplementedBy(DefaultVerificationManager.class)
public interface VerificationManager {
	
	void save(Verification verification);
	
	void delete(Verification verification);
	
	Collection<Verification> findBy(PullRequest request, String commit);
	
	Verification findBy(PullRequest request, String commit, String configuration);
	
	Verification.Status getOverallStatus(Collection<Verification> verifications);
}