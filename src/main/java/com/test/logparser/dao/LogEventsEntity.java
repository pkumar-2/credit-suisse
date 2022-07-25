/**
 * 
 */
package com.test.logparser.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author pakumar
 *
 */
@Entity
@Table(name = "LOGEVENTS")
public class LogEventsEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "RECID")
    private int recId;
	
    @Column(name = "ID")
	private String id;
    
    @Column(name = "EVAENTDURATION")
    private Integer eventDuration;
    
    @Column(name = "ALERT")
    private String alert;
	/**
	 * @return the recId
	 */
	public int getRecId() {
		return recId;
	}
	/**
	 * @param recId the recId to set
	 */
	public void setRecId(int recId) {
		this.recId = recId;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the eventDuration
	 */
	public Integer getEventDuration() {
		return eventDuration;
	}
	/**
	 * @param eventDuration the eventDuration to set
	 */
	public void setEventDuration(Integer eventDuration) {
		this.eventDuration = eventDuration;
	}
	/**
	 * @return the alert
	 */
	public String getAlert() {
		return alert;
	}
	/**
	 * @param alert the alert to set
	 */
	public void setAlert(String alert) {
		this.alert = alert;
	}
	@Override
	public String toString() {
		return "LogEventsEntity [recId=" + recId + ", id=" + id + ", eventDuration=" + eventDuration + ", alert=" + alert
				+ "]";
	}
    
    
    
    
    
}
