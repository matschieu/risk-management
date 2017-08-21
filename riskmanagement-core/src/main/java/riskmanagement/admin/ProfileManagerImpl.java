package riskmanagement.admin;

import java.util.List;

import javax.ejb.Stateless;

/**
 * @author Matschieu
 *
 */
@Stateless(name = "riskmanagement.admin.ProfileManager")
public class ProfileManagerImpl implements ProfileManager {

	@Override
	public Integer createProfile(Profile profile) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateProfile(Profile profile) throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public List<Profile> findAllProfiles(String accountId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Profile findProfile(Integer profileId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Profile findProfile(String accountId, String profileName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Profile findActiveProfile(String accountId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteProfile(Integer profileId) throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void deleteProfile(String accountId, String profileName) throws Exception {
		// TODO Auto-generated method stub
	}

}
