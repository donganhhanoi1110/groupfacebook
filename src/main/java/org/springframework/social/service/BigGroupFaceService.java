package org.springframework.social.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.social.dao.BigGroupFaceDAO;
import org.springframework.social.model.*;

/**
 * BigGroupFace Service class call method from DAO for controller to do get Data
 * or business logic
 * 
 * @author Anh Minh Nguyen
 *
 */
@Service("bigGroupFaceService")
public class BigGroupFaceService {

	BigGroupFaceDAO BigGroupFaceDAO;

	public BigGroupFaceDAO getBigGroupFaceDAO() {
		return BigGroupFaceDAO;
	}

	@Autowired
	public void setBigGroupFaceDAO(BigGroupFaceDAO BigGroupFaceDAO) {
		this.BigGroupFaceDAO = BigGroupFaceDAO;
	}

	public BigGroupFace getBigGroupFace(int id) {
		return this.getBigGroupFaceDAO().getBigGroupFace(id);
	}

	public List<BigGroupFace> getAllBigGroupFace() {
		return this.getBigGroupFaceDAO().getAllBigGroupFace();
	}

	public BigGroupFace getBigGroupFacebyBigGroupFaceName(
			String BigGroupFacename) {
		return this.getBigGroupFaceDAO().getBigGroupFacebyBigGroupFaceName(
				BigGroupFacename);
	}

	public boolean createBigGroupFace(BigGroupFace BigGroupFace) {
		return this.getBigGroupFaceDAO().createBigGroupFace(BigGroupFace);
	}

	public boolean updateBigGroupFace(BigGroupFace BigGroupFace) {
		return this.getBigGroupFaceDAO().updateBigGroupFace(BigGroupFace);
	}

	/**
	 * Delete BigGroupFace by their Id.
	 *
	 * @param BigGroupFaceId
	 *            the BigGroupFace Id.
	 */
	public boolean deleteBigGroupFaceById(int BigGroupFaceId) {
		return this.getBigGroupFaceDAO().deleteBigGroupFaceById(BigGroupFaceId);
	}

	/**
	 * Delete BigGroupFace entity.
	 *
	 * @param BigGroupFace
	 *            the object to be deleted.
	 */
	public boolean deleteBigGroupFace(BigGroupFace BigGroupFace) {
		return this.getBigGroupFaceDAO().deleteBigGroupFace(BigGroupFace);
	}

	// Hiep add 9:04 PM date 20/04/2015
	/**
	 * get list of BigGroupFace with clientId
	 * 
	 * @param clientId
	 * @return
	 */
	public List<BigGroupFace> getAllBigGroupFaceOfClient(String clientId) {

		return this.BigGroupFaceDAO.getAllBigGroupFaceOfClient(clientId);
	}

	// Hiep add 9:04 PM date 20/04/2015
	/**
	 * check nameBigGroupFace already exist ???
	 * @param nameBigGroupFace
	 * @return
	 */
	public boolean isExistNameBigGroupFace(String nameBigGroupFace) {
		return this.BigGroupFaceDAO.isExistNameBigGroupFace(nameBigGroupFace);
	}
}
