/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.machrent.service;

import cz.muni.fi.pa165.machrent.BeanMappingService;
import cz.muni.fi.pa165.machrent.config.ServiceConfiguration;
import cz.muni.fi.pa165.machrent.dto.RevisionDto;
import cz.muni.fi.pa165.machrent.entities.Machine;
import cz.muni.fi.pa165.machrent.entities.RentalUser;
import cz.muni.fi.pa165.machrent.entities.Revision;
import cz.muni.fi.pa165.machrent.enums.LegalPersonality;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Peter Benus
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class BeanMappingServiceTest extends AbstractTestNGSpringContextTests
{
   
    @Autowired
    private BeanMappingService beanMappingService;
    
    private List<Revision> revisions = new ArrayList<Revision>(); 
    private Date timestamp;
    
    @BeforeMethod
    public void createRevision(){
        Revision revision;
        RentalUser mechanic;
        Machine machine;
        revision = new Revision();
        machine = new Machine();
        machine.setName("machine");
        revision.setMachine(machine);
        mechanic = new RentalUser();
        mechanic.setName("Janko");
        mechanic.setEmail("janko@mail.com");
        mechanic.setUsername("johny");
        mechanic.setLegalPersonality(LegalPersonality.NATURAL);
        revision.setMechanic(mechanic);
        revision.setNote("revision1");
        timestamp = new Date();
        revision.setRevisionDate(timestamp);
        
        revisions.add(revision);
        
        Revision revision2 = new Revision();
        Machine machine2 = new Machine();
        machine2.setName("machine2");
        revision2.setMachine(machine2);
        RentalUser mechanic2 = new RentalUser();
        mechanic2.setName("Ferko");
        mechanic2.setEmail("ferko@mail.com");
        mechanic2.setUsername("fery");
        mechanic2.setLegalPersonality(LegalPersonality.JURIDICAL);
        revision2.setMechanic(mechanic2);
        revision2.setNote("revision2");
        Date timestamp2 = new Date();
        revision2.setRevisionDate(timestamp2);
        
        revisions.add(revision2);
    }
    
    @Test
    public void shouldMapRevisions(){
    	List<RevisionDto> cdtos = beanMappingService.mapTo(revisions, RevisionDto.class);
    	Assert.assertEquals(cdtos.get(1).getNote(), "revision2");
    	
    }
}
