package cz.muni.fi.pa165.machrent.dto;

import cz.muni.fi.pa165.machrent.entities.Machine;
import cz.muni.fi.pa165.machrent.entities.RentalUser;
import cz.muni.fi.pa165.machrent.entities.Revision;

import java.util.Date;

/**
 * Created by zschwarz on 11/20/16.
 */
public class RevisionDto {
	private Long id;
	private String note;
	private Date revisionDate;
	private RentalUserDto mechanic;
	private MachineDto machine;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getRevisionDate() {
		return revisionDate;
	}

	public void setRevisionDate(Date revisionDate) {
		this.revisionDate = revisionDate;
	}

	public RentalUserDto getMechanic() {
		return mechanic;
	}

	public void setMechanic(RentalUserDto mechanic) {
		this.mechanic = mechanic;
	}

	public MachineDto getMachine() {
		return machine;
	}

	public void setMachine(MachineDto machine) {
		this.machine = machine;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof RevisionDto)) return false;

		RevisionDto that = (RevisionDto) o;

		if (getRevisionDate() != null ? !getRevisionDate().equals(that.getRevisionDate()) : that.getRevisionDate() != null)
			return false;
		return getMachine() != null ? getMachine().equals(that.getMachine()) : that.getMachine() == null;

	}

	@Override
	public int hashCode() {
		int result = getRevisionDate() != null ? getRevisionDate().hashCode() : 0;
		result = 31 * result + (getMachine() != null ? getMachine().hashCode() : 0);
		return result;
	}
}
