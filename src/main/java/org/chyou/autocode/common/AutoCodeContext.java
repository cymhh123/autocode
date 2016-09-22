package org.chyou.autocode.common;


import org.durcframework.common.UserContext;
import org.chyou.autocode.modules.user.entity.BackUser;

public enum AutoCodeContext {
	INSTANCE;

	public static AutoCodeContext getInstance() {
		return INSTANCE;
	}

	public BackUser getUser() {
		return UserContext.getInstance().getUser();
	}

	public void setUser(BackUser backUser) {
		UserContext.getInstance().setUser(backUser);
	}

	public boolean isAdmin() {
		return "admin".equals(getUser().getUsername());
	}
}
