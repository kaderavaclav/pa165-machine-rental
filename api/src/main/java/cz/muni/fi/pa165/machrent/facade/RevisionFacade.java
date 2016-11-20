package cz.muni.fi.pa165.machrent.facade;

import cz.muni.fi.pa165.machrent.dto.RevisionCreateDto;
import cz.muni.fi.pa165.machrent.dto.RevisionDto;

import java.util.List;

/**
 * Created by zschwarz on 11/20/16.
 */
public interface RevisionFacade {

	/**
	 * Creates new revision.
	 * @param r revision to be created
	 * @return id of created revision
	 */
	Long createRevision(RevisionCreateDto r);

	/**
	 *Updates existing revision.
	 * @param r revision to be updated
	 */
	void updateRevision(RevisionDto r);

	/**
	 * Deletes existing revision.
	 * @param id of revision to be deleted
	 */
	void deleteRevision(Long id);

	/**
	 * Returns all revisions.
	 * @return all revisions
	 */
	List<RevisionDto> findAllRevisions();

	/**
	 * Returns revision with given id
	 * @param id of revision
	 * @return revision with given id
	 */
	RevisionDto findById(Long id);
}
