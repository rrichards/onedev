package io.onedev.server.search.entity.build;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import io.onedev.server.OneException;
import io.onedev.server.model.Build;
import io.onedev.server.model.User;
import io.onedev.server.search.entity.EntityCriteria;
import io.onedev.server.util.query.BuildQueryConstants;

public class CancelledByMeCriteria extends EntityCriteria<Build> {

	private static final long serialVersionUID = 1L;

	@Override
	public Predicate getPredicate(Root<Build> root, CriteriaBuilder builder) {
		if (User.get() != null) {
			Path<User> attribute = root.get(BuildQueryConstants.ATTR_CANCELLER);
			return builder.equal(attribute, User.get());
		} else {
			throw new OneException("Please login to perform this query");
		}
	}

	@Override
	public boolean matches(Build build) {
		if (User.get() != null)
			return User.get().equals(build.getCanceller());
		else
			throw new OneException("Please login to perform this query");
	}

	@Override
	public String asString() {
		return BuildQuery.getRuleName(BuildQueryLexer.CancelledByMe);
	}

}
