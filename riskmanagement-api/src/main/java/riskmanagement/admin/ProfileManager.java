package riskmanagement.admin;

import java.util.List;

import javax.ejb.Local;

import riskmanagement.common.Stage;

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
	 * @param stage
	 * @return List<Profile>
	 */
	List<Profile> findAllProfiles(String accountId, Stage stage);

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
	 * @param stage
	 * @return Profile
	 */
	Profile findProfile(String accountId, String profileName, Stage stage);

	/**
	 *
	 * @param accountId
	 * @param stage
	 * @return Profile
	 */
	Profile findActiveProfile(String accountId, Stage stage);

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
	 * @param stage
	 * @throws Exception
	 */
	void deleteProfile(String accountId, String profileName, Stage stage) throws Exception;
}
