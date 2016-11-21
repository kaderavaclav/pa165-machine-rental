package cz.muni.fi.pa165.machrent;

import cz.muni.fi.pa165.machrent.entities.Revision;

import java.util.List;

/**
 * Created by zschwarz on 11/20/16.
 */
public interface RevisionService {

	/**
	 * Creates new revision.
	 * @param r revision to be created
	 * @return id of created revision
	 */
	void createRevision(Revision r);

	/**
	 *Updates existing revision.
	 * @param r revision to be updated
	 */
	void updateRevision(Revision r);

	/**
	 * Deletes existing revision.
	 * @param r revision to be deleted
	 */
	void deleteRevision(Revision r);

	/**
	 * Returns all revisions.
	 * @return all revisions
	 */
	List<Revision> findAllRevisions();

	/**
	 * Returns revision with given id
	 * @param id of revision
	 * @return revision with given id
	 */
	Revision findById(Long id);
}
