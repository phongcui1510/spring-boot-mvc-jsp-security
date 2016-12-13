package home.seminar.proof.service.currentuser;

import home.seminar.proof.domain.CurrentUser;

public interface CurrentUserService {

    boolean canAccessUser(CurrentUser currentUser, Long userId);

}
