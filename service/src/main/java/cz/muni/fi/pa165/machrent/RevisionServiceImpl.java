package cz.muni.fi.pa165.machrent;

import cz.muni.fi.pa165.machrent.dao.RevisionDao;
import cz.muni.fi.pa165.machrent.entities.Revision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zschwarz on 11/20/16.
 */
@Service
public class RevisionServiceImpl implements RevisionService {

	@Autowired
	private RevisionDao revisionDao;


	@Override
	public void createRevision(Revision r) {
		revisionDao.create(r);
	}

	@Override
	public void deleteRevision(Revision r) {
		revisionDao.delete(r);

	}

	@Override
	public void updateRevision(Revision r) {
		revisionDao.update(r);
	}

	@Override
	public List<Revision> findAllRevisions() {
		return revisionDao.findAll();
	}

	@Override
	public List<Revision> findAllMachineRevisions(long machineId){
		return revisionDao.findAllByMachineId(machineId);
	}

	@Override
	public Revision findById(Long id) {

		return  revisionDao.findById(id);
	}
}
