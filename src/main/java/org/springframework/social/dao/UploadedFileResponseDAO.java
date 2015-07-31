package org.springframework.social.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.social.model.*;

/**
 * DAO class for UploadedFileResponse entity. This class contains all methods that
 * inserts/updates/deletes UploadedFileResponse info
 * 
 * @author Anh Minh Nguyen
 *
 */
@Repository("uploadedFileResponseDAO")
@Transactional(propagation = Propagation.REQUIRED)
public class UploadedFileResponseDAO {
	@PersistenceContext
	public EntityManager entityManager;

	@Transactional

	public UploadedFileResponse getUploadedFileResponse(int id) {
		UploadedFileResponse uploadedFile = null;
		try {
			uploadedFile = entityManager.find(UploadedFileResponse.class, id);
			if (uploadedFile == null) {
				throw new EntityNotFoundException("Can't find UploadedFileResponse for ID "
						+ id);
			}
			System.out.println(uploadedFile.toString() + "getUploadedFileResponse-DAO");
		} catch (Exception e) {
			System.out.println("\nGetting UploadedFileResponse had Errors" + "*_"
					+ e.getMessage() + "*_");
		}
		return uploadedFile;
	}

	public List<UploadedFileResponse> getAllUploadedFileResponse() {
		List<UploadedFileResponse> list = new ArrayList<UploadedFileResponse>();
		try {
			TypedQuery<UploadedFileResponse> query = entityManager.createQuery("SELECT c FROM "
					+ UploadedFileResponse.class.getName() + " c", UploadedFileResponse.class);

			list = query.getResultList();
			System.out.println("Get All UploadedFileResponses");
		} catch (Exception e) {
			System.out.println("\nGet Error " + "*_" + e.getMessage() + "*_");
			e.printStackTrace();
		}
		return list;

	}

	@Transactional

	public boolean createUploadedFileResponse(UploadedFileResponse uploadedFileResponse) {
		boolean check = false;

		try {
			if (uploadedFileResponse != null) {
			// Insert a row to uploadedFileResponse table
			entityManager.persist(uploadedFileResponse);
			check = true;
			}
		} catch (Exception e) {
			System.out.println("\nGet Error with Create UploadedFileResponse " + "*_"
					+ e.getMessage() + "*_");
			e.printStackTrace();
			check=false;
		}
		return check;
	}

	/**
	 * Update UploadedFileResponse information.
	 *
	 * @param UploadedFileResponse
	 *            an UploadedFileResponse to be updated.
	 */
	@Transactional
	public boolean updateUploadedFileResponse(UploadedFileResponse uploadedFileResponse) {

		boolean check = false;
		try {
			if (uploadedFileResponse != null) {
			entityManager.merge(uploadedFileResponse);
			check = true;
			System.out.println("UploadedFileResponse " + uploadedFileResponse.getIdFile() + "updated");
			}
		} catch (Exception e) {
			System.out.println("\nUpdate UploadedFileResponse get Error " + "*_"
					+ e.getMessage() + "*_");
			e.printStackTrace();
		}
		return check;
	}

	/**
	 * Delete UploadedFileResponse by their Id.
	 *
	 * @param UploadedFileResponseId
	 *            the UploadedFileResponse Id.
	 */
	@Transactional
	public boolean deleteUploadedFileResponseById(int uploadedFileResponseId) {
		boolean check = false;
		try {
			UploadedFileResponse UploadedFileResponse = entityManager.find(UploadedFileResponse.class, uploadedFileResponseId);
			if (UploadedFileResponse == null) {
				check = false;
				throw new EntityNotFoundException("Can't find UploadedFileResponse for ID "
						+ uploadedFileResponseId);
			}
			System.out.println(UploadedFileResponse.toString());
			if (UploadedFileResponse != null) {

				entityManager.remove(UploadedFileResponse);
				check = true;
				System.out.println("delete UploadedFileResponse by ID");
			}
		} catch (Exception e) {

			System.out.println("\nDelete UploadedFileResponse by ID get Error " + "*_"
					+ e.getMessage() + "*_");
			e.printStackTrace();
		}
		return check;
	}

	/**
	 * Delete UploadedFileResponse entity.
	 *
	 * @param UploadedFileResponse
	 *            the object to be deleted.
	 */
	public boolean deleteUploadedFileResponse(UploadedFileResponse uploadedFileResponse) {
		boolean check = false;
		try {
			if (uploadedFileResponse != null) {
			entityManager.remove(uploadedFileResponse);
			check = true;
			System.out.println("delete UploadedFileResponse by UploadedFileResponse");
			}
		} catch (Exception e) {
			System.out.println("\nDelete UploadedFileResponse get Error" + "*_"
					+ e.getMessage() + "*_");
			e.printStackTrace();
		}
		return check;
	}

	/**
	 * 
	 * check idUploadedFileResponse already exist ???
	 * @param idUploadedFileResponse
	 * @return
	 */
	public boolean isExistIdUploadedFileResponse(String name) {
		UploadedFileResponse UploadedFileResponse = null;
		try {
			if(StringUtils.isBlank(name)) return false;
			TypedQuery<UploadedFileResponse> query = entityManager.createQuery("SELECT c FROM "
					+ UploadedFileResponse.class.getName() + " c where c.name =:name",
					UploadedFileResponse.class);
			query.setParameter("name", name);
			UploadedFileResponse = query.getSingleResult();
			if(UploadedFileResponse == null) {
				return false;
			}
			else {
				return true;
			}
		} catch (Exception e) {
			System.out.println("\nGetting UploadedFileResponse had Errors" + "*_"
					+ e.getMessage() + "*_");
			e.printStackTrace();
			return false;

		}
		
	}
	
	/**
	 * 
	 * get UploadedFileResponse by name and type
	 * @param String name
	 * @param String type
	 * @return
	 */
	public UploadedFileResponse getFileByNameAndType(String name, String type) {
		UploadedFileResponse uploadedFileResponse = null;
		try {
			if(StringUtils.isBlank(name) || StringUtils.isBlank(type)) return null;
			TypedQuery<UploadedFileResponse> query = entityManager.createQuery("SELECT c FROM "
					+ UploadedFileResponse.class.getName() + " c where c.name =:name and c.type =:type",
					UploadedFileResponse.class);
			query.setParameter("name", name);
			query.setParameter("type", type);
			uploadedFileResponse = query.getSingleResult();
			if(uploadedFileResponse == null) {
				return null;
			}
			else {
				return uploadedFileResponse;
			}
		} catch (Exception e) {
			System.out.println("\nGetting UploadedFileResponse had Errors" + "*_"
					+ e.getMessage() + "*_");
			return null;

		}
		
	}
	
	/**
	 * 
	 * get UploadedFileResponse by fileId
	 * @param String type
	 * @return
	 */
	public UploadedFileResponse getFileByFileId(String idFile) {
		UploadedFileResponse uploadedFileResponse = null;
		try {
			if(StringUtils.isBlank(idFile) ) return null;
			TypedQuery<UploadedFileResponse> query = entityManager.createQuery("SELECT c FROM "
					+ UploadedFileResponse.class.getName() + " c where c.idFile =:idFile",
					UploadedFileResponse.class);
			query.setParameter("idFile", idFile);
			uploadedFileResponse = query.getSingleResult();
			if(uploadedFileResponse == null) {
				return null;
			}
			else {
				return uploadedFileResponse;
			}
		} catch (Exception e) {
			System.out.println("\nGetting UploadedFileResponse had Errors" + "*_"
					+ e.getMessage() + "*_");
			return null;

		}
		
	}

}
