package org.springframework.social.model;

import java.io.Serializable;
import java.net.URL;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import facebook4j.Cover;
import facebook4j.Picture;
import facebook4j.User;

/**
 * Entity class for user table. This is a simple POJO class with annotations
 * to define mapping with DB table
 * 
 * @author Anh Minh Nguyen
 *
 */
@Entity
@Table(name = "membergroup", uniqueConstraints = {
		@UniqueConstraint(columnNames = "member_id")})
public class MemberGroup implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	@Column(name = "id_group", length=30)
	private String idGroup;
	@Column(name = "member_id", length=30)
	private String memberId;
	@Column(name = "name")
    private String name;
	@Column(name = "first_name")
    private String firstName;
	@Column(name = "middle_name")
    private String middleName;
	@Column(name = "last_name")
    private String lastName;
	@Column(name = "gender")
	private String gender;
	@Column(name = "locale")
	private String locale;
	@Column(name = "languages")
    private String languages;
	@Column(name = "link")
    private String link;
	@Column(name = "username")
    private String username;
	@Column(name = "")
    private double timezone;
	@Column(name = "updated_time")
    private String updatedTime;
	@Column(name = "verified")
    private boolean verified;
	@Column(name = "bio")
    private String bio;
	@Column(name = "birthday")
    private String birthday;
	@Column(name = "education")
    private String education;
	@Column(name = "email")
    private String email;
	@Column(name = "hometown")
    private String hometown;
	@Column(name = "interested_in")
    private String interestedIn;
	@Column(name = "location")
    private String location;
	@Column(name = "political")
    private String political;
	@Column(name = "favorite_athletes")
    private String favoriteAthletes;
	@Column(name = "favorite_teams")
    private String favoriteTeams;
	@Column(name = "picture")
    private String picture;
	@Column(name = "quotes")
    private String quotes;
	@Column(name = "relationship_status")
    private String relationshipStatus;
	@Column(name = "relition")
    private String religion;
	@Column(name = "significant_other")
    private String significantOther;
    @Column(name = "website")
    private String website;
    @Column(name = "work")
    private String work;
    
    @ManyToOne
	@JoinColumn(name = "groupFace")
	private GroupFace groupFace;
    
    
	public String getIdGroup() {
		return idGroup;
	}
	public void setIdGroup(String idGroup) {
		this.idGroup = idGroup;
	}
	public GroupFace getGroupFace() {
		return groupFace;
	}
	public void setGroupFace(GroupFace groupFace) {
		this.groupFace = groupFace;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public String getLanguages() {
		return languages;
	}
	public void setLanguages(String languages) {
		this.languages = languages;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public double getTimezone() {
		return timezone;
	}
	public void setTimezone(double timezone) {
		this.timezone = timezone;
	}
	public String getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}
	public boolean isVerified() {
		return verified;
	}
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHometown() {
		return hometown;
	}
	public void setHometown(String hometown) {
		this.hometown = hometown;
	}
	public String getInterestedIn() {
		return interestedIn;
	}
	public void setInterestedIn(String interestedIn) {
		this.interestedIn = interestedIn;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getPolitical() {
		return political;
	}
	public void setPolitical(String political) {
		this.political = political;
	}
	public String getFavoriteAthletes() {
		return favoriteAthletes;
	}
	public void setFavoriteAthletes(String favoriteAthletes) {
		this.favoriteAthletes = favoriteAthletes;
	}
	public String getFavoriteTeams() {
		return favoriteTeams;
	}
	public void setFavoriteTeams(String favoriteTeams) {
		this.favoriteTeams = favoriteTeams;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getQuotes() {
		return quotes;
	}
	public void setQuotes(String quotes) {
		this.quotes = quotes;
	}
	public String getRelationshipStatus() {
		return relationshipStatus;
	}
	public void setRelationshipStatus(String relationshipStatus) {
		this.relationshipStatus = relationshipStatus;
	}
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	public String getSignificantOther() {
		return significantOther;
	}
	public void setSignificantOther(String significantOther) {
		this.significantOther = significantOther;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getWork() {
		return work;
	}
	public void setWork(String work) {
		this.work = work;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public MemberGroup(int id, String idGroup, String memberId, String name, String firstName,
			String middleName, String lastName, String gender, String locale,
			String languages, String link, String username, double timezone,
			String updatedTime, boolean verified, String bio, String birthday,
			String education, String email, String hometown,
			String interestedIn, String location, String political,
			String favoriteAthletes, String favoriteTeams, String picture,
			String quotes, String relationshipStatus, String religion,
			String significantOther, String website, String work, GroupFace groupFace) {
		super();
		this.id = id;
		this.idGroup = idGroup;
		this.memberId = memberId;
		this.name = name;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.gender = gender;
		this.locale = locale;
		this.languages = languages;
		this.link = link;
		this.username = username;
		this.timezone = timezone;
		this.updatedTime = updatedTime;
		this.verified = verified;
		this.bio = bio;
		this.birthday = birthday;
		this.education = education;
		this.email = email;
		this.hometown = hometown;
		this.interestedIn = interestedIn;
		this.location = location;
		this.political = political;
		this.favoriteAthletes = favoriteAthletes;
		this.favoriteTeams = favoriteTeams;
		this.picture = picture;
		this.quotes = quotes;
		this.relationshipStatus = relationshipStatus;
		this.religion = religion;
		this.significantOther = significantOther;
		this.website = website;
		this.work = work;
		this.groupFace = groupFace;
	}
    
    public MemberGroup(){
    	
    }
	@Override
	public String toString() {
		return "MemberGroup [id=" + id + ", memberId=" + memberId + ", name="
				+ name + ", firstName=" + firstName + ", middleName="
				+ middleName + ", lastName=" + lastName + ", gender=" + gender
				+ ", locale=" + locale + ", languages=" + languages + ", link="
				+ link + ", username=" + username + ", timezone=" + timezone
				+ ", updatedTime=" + updatedTime + ", verified=" + verified
				+ ", bio=" + bio + ", birthday=" + birthday + ", education="
				+ education + ", email=" + email + ", hometown=" + hometown
				+ ", interestedIn=" + interestedIn + ", location=" + location
				+ ", political=" + political + ", favoriteAthletes="
				+ favoriteAthletes + ", favoriteTeams=" + favoriteTeams
				+ ", picture=" + picture + ", quotes=" + quotes
				+ ", relationshipStatus=" + relationshipStatus + ", religion="
				+ religion + ", significantOther=" + significantOther
				+ ", website=" + website + ", work=" + work + "]";
	}
    
    
}
