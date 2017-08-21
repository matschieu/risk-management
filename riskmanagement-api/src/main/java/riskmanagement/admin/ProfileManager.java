package riskmanagement.admin;

import java.util.List;

import javax.ejb.Local;

/**
 * @author Matschieu
 *
 */
@Local
public interface ProfileManager {

	/**
	 *
	 * @param profile
	 * @return Integer
	 * @throws Exception
	 */
	Integer createProfile(Profile profile) throws Exception;

	/**
	 *
	 * @param profile
	 * @throws Exception
	 */
	void updateProfile(Profile profile) throws Exception;

	/**
	 *
	 * @param accountId
	 * @return List<Profile>
	 */
	List<Profile> findAllProfiles(String accountId);

	/**
	 *
	 * @param profileId
	 * @return Profile
	 */
	Profile findProfile(Integer profileId);

	/**
	 *
	 * @param accountId
	 * @param profileName
	 * @return Profile
	 */
	Profile findProfile(String accountId, String profileName);

	/**
	 *
	 * @param accountId
	 * @return Profile
	 */
	Profile findActiveProfile(String accountId);

	/**
	 *
	 * @param profileId
	 * @throws Exception
	 */
	void deleteProfile(Integer profileId) throws Exception;

	/**
	 *
	 * @param accountId
	 * @param profileName
	 * @throws Exception
	 */
	void deleteProfile(String accountId, String profileName) throws Exception;
}
