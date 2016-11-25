package cz.muni.fi.pa165.machrent.facade;

import cz.muni.fi.pa165.machrent.BeanMappingService;
import cz.muni.fi.pa165.machrent.RevisionService;
import cz.muni.fi.pa165.machrent.dto.RevisionCreateDto;
import cz.muni.fi.pa165.machrent.dto.RevisionDto;
import cz.muni.fi.pa165.machrent.entities.Revision;
import cz.muni.fi.pa165.machrent.exceptions.MachrentServiceException;
import cz.muni.fi.pa165.machrent.exceptions.RevisionServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zschwarz on 11/20/16.
 */
@Service
@Transactional
public class RevisionFacadeImpl implements RevisionFacade {

	@Autowired
	private BeanMappingService beanMappingService;

	@Autowired
	private RevisionService revs;

	@Override
	public Long createRevision(RevisionCreateDto r) {
		if (r == null) {
			throw new IllegalArgumentException("Revision is null.");
		}
		Revision rev = beanMappingService.mapTo(r, Revision.class);
		revs.createRevision(rev);
		return rev.getId();
	}

	@Override
	public void updateRevision(RevisionDto r) {
		if (r == null) {
			throw new IllegalArgumentException("Revision is null.");
		}
		Revision rev = beanMappingService.mapTo(r, Revision.class);
		if (revs.findById(rev.getId()) == null) {
			throw new RevisionServiceException("Revision not found.");
		}
		revs.updateRevision(rev);


	}

	@Override
	public void deleteRevision(Long id) {
		Revision r = revs.findById(id);
		if (r == null) {
			throw new RevisionServiceException("Revision not found.");
		}
		revs.deleteRevision(r);
	}

	@Override
	public List<RevisionDto> findAllRevisions() {
		return beanMappingService.mapTo(revs.findAllRevisions(), RevisionDto.class);
	}

	@Override
	public RevisionDto findById(Long id) {
		Revision r = revs.findById(id);
		if (r == null) {
			throw new RevisionServiceException("Revision not found.");
		}
		return beanMappingService.mapTo(r,RevisionDto.class);
	}
}
