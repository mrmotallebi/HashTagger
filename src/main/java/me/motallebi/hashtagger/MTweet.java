/**
 * 
 */
package me.motallebi.hashtagger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import twitter4j.ExtendedMediaEntity;
import twitter4j.GeoLocation;
import twitter4j.HashtagEntity;
import twitter4j.MediaEntity;
import twitter4j.Place;
import twitter4j.RateLimitStatus;
import twitter4j.Scopes;
import twitter4j.Status;
import twitter4j.SymbolEntity;
import twitter4j.URLEntity;
import twitter4j.User;
import twitter4j.UserMentionEntity;

/**
 * @author mrmotallebi
 *
 */
public class MTweet implements Status {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6745170506678156898L;
	private final long id;
	private final String text;
	private final MUser user;
	private final String lang;
	private final int retweetCount;
	private final Date createdAt;
	private final int hashCode;
	private final HashtagEntity[] hashtagEntities;

	private MTweet(Status s, boolean lowerCase) {
		this.id = s.getId();
		this.text = lowerCase ? s.getText().toLowerCase() : s.getText();
		this.user = new MUser(s.getUser());
		this.lang = s.getLang();
		this.retweetCount = s.getRetweetCount();
		this.createdAt = s.getCreatedAt();
		this.hashCode = s.hashCode();

		this.hashtagEntities = new MHashtag[s.getHashtagEntities().length];
		HashtagEntity[] inHashtags = s.getHashtagEntities();
		for (int i = 0; i < this.hashtagEntities.length; i++) {
			this.hashtagEntities[i] = new MHashtag(inHashtags[i]);
		}
	}

	private MTweet(Status s) {
		this(s, true);
	}

	public static MTweet asMTweet(Status s) {
		return new MTweet(s, true);
	}

	public static MTweet asMTweet(Status s, boolean allLowerCase) {
		return new MTweet(s, allLowerCase);
	}

	public static List<Status> asMTweetList(List<? extends Status> oldStatus) {
		List<Status> returnList = new ArrayList<>(oldStatus.size());
		for (Status s : oldStatus) {
			MTweet mtweet = new MTweet(s);
			returnList.add(mtweet);
		}
		return returnList;
	}

	static class MHashtag implements HashtagEntity {
		/**
		 * 
		 */
		private static final long serialVersionUID = 5793918036463144714L;
		private HashtagEntity wrapped;

		public MHashtag(HashtagEntity h) {
			this.wrapped = h;
		}

		@Override
		public int getEnd() {
			return wrapped.getEnd();
		}

		@Override
		public int getStart() {
			return wrapped.getStart();
		}

		@Override
		public String getText() {
			return wrapped.getText().toLowerCase();
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime
					* result
					+ ((wrapped == null) ? 0 : wrapped.getText().toLowerCase()
							.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof MHashtag)) {
				return false;
			}
			MHashtag other = (MHashtag) obj;
			if (wrapped == null) {
				if (other.wrapped != null) {
					return false;
				}
			} else if (!wrapped.getText().toLowerCase()
					.equals(other.wrapped.getText().toLowerCase())) {
				return false;
			}
			return true;
		}

		@Override
		public String toString() {
			return this.getText();
		}
	}

	static class MUser implements User {
		/**
		 * 
		 */
		private static final long serialVersionUID = -6326103798066029148L;
		private final long id;
		private final int followersCount;
		private final int hashCode;

		public MUser(User u) {
			this.id = u.getId();
			this.followersCount = u.getFollowersCount();
			this.hashCode = u.hashCode();
		}

		@Override
		public int getFollowersCount() {
			return this.followersCount;
		}

		@Override
		public long getId() {
			return this.id;
		}

		@Override
		public int hashCode() {
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof MTweet.MUser))
				return false;
			MUser u = (MUser) obj;
			return this.id == u.id;
		}

		@Override
		public int compareTo(User o) {
			if (o instanceof MUser) {
				if (this == o || this.equals(o))
					return 0;
			} else if (o != null)
				return o.compareTo(this);
			return -1;
		}

		@Override
		public String toString() {
			return String.valueOf(this.getId());
		}

		@Override
		public int getFriendsCount() {
			return 0;
		}

		@Override
		public int getAccessLevel() {
			return 0;
		}

		@Override
		public RateLimitStatus getRateLimitStatus() {
			return null;
		}

		@Override
		public String getBiggerProfileImageURL() {
			return null;
		}

		@Override
		public String getBiggerProfileImageURLHttps() {
			return null;
		}

		@Override
		public Date getCreatedAt() {
			return null;
		}

		@Override
		public String getDescription() {
			return null;
		}

		@Override
		public URLEntity[] getDescriptionURLEntities() {
			return null;
		}

		@Override
		public int getFavouritesCount() {
			return 0;
		}

		@Override
		public String getLang() {
			return null;
		}

		@Override
		public int getListedCount() {
			return 0;
		}

		@Override
		public String getLocation() {
			return null;
		}

		@Override
		public String getMiniProfileImageURL() {
			return null;
		}

		@Override
		public String getMiniProfileImageURLHttps() {
			return null;
		}

		@Override
		public String getName() {
			return null;
		}

		@Override
		public String getOriginalProfileImageURL() {
			return null;
		}

		@Override
		public String getOriginalProfileImageURLHttps() {
			return null;
		}

		@Override
		public String getProfileBackgroundColor() {
			return null;
		}

		@Override
		public String getProfileBackgroundImageURL() {
			return null;
		}

		@Override
		public String getProfileBackgroundImageUrlHttps() {
			return null;
		}

		@Override
		public String getProfileBannerIPadRetinaURL() {
			return null;
		}

		@Override
		public String getProfileBannerIPadURL() {
			return null;
		}

		@Override
		public String getProfileBannerMobileRetinaURL() {
			return null;
		}

		@Override
		public String getProfileBannerMobileURL() {
			return null;
		}

		@Override
		public String getProfileBannerRetinaURL() {
			return null;
		}

		@Override
		public String getProfileBannerURL() {
			return null;
		}

		@Override
		public String getProfileImageURL() {
			return null;
		}

		@Override
		public String getProfileImageURLHttps() {
			return null;
		}

		@Override
		public String getProfileLinkColor() {
			return null;
		}

		@Override
		public String getProfileSidebarBorderColor() {
			return null;
		}

		@Override
		public String getProfileSidebarFillColor() {
			return null;
		}

		@Override
		public String getProfileTextColor() {
			return null;
		}

		@Override
		public String getScreenName() {
			return null;
		}

		@Override
		public Status getStatus() {
			return null;
		}

		@Override
		public int getStatusesCount() {
			return 0;
		}

		@Override
		public String getTimeZone() {
			return null;
		}

		@Override
		public String getURL() {
			return null;
		}

		@Override
		public URLEntity getURLEntity() {
			return null;
		}

		@Override
		public int getUtcOffset() {
			return 0;
		}

		@Override
		public String[] getWithheldInCountries() {
			return null;
		}

		@Override
		public boolean isContributorsEnabled() {
			return false;
		}

		@Override
		public boolean isDefaultProfile() {
			return false;
		}

		@Override
		public boolean isDefaultProfileImage() {
			return false;
		}

		@Override
		public boolean isFollowRequestSent() {
			return false;
		}

		@Override
		public boolean isGeoEnabled() {
			return false;
		}

		@Override
		public boolean isProfileBackgroundTiled() {
			return false;
		}

		@Override
		public boolean isProfileUseBackgroundImage() {
			return false;
		}

		@Override
		public boolean isProtected() {
			return false;
		}

		@Override
		public boolean isShowAllInlineMedia() {
			return false;
		}

		@Override
		public boolean isTranslator() {
			return false;
		}

		@Override
		public boolean isVerified() {
			return false;
		}

	}

	@Override
	public String getText() {
		return this.text;
	}

	@Override
	public User getUser() {
		return this.user;
	}

	@Override
	public HashtagEntity[] getHashtagEntities() {
		return this.hashtagEntities;
	}

	@Override
	public int compareTo(Status o) {
		return this.createdAt.compareTo(o.getCreatedAt());
	}

	@Override
	public Date getCreatedAt() {
		return this.createdAt;
	}

	@Override
	public long getId() {
		return this.id;
	}

	@Override
	public String getLang() {
		return this.lang;
	}

	@Override
	public int getRetweetCount() {
		return this.retweetCount;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Status))
			return false;
		Status s = (Status) obj;
		return this.id == s.getId();
	}

	@Override
	public int hashCode() {
		return this.hashCode;
	}

	@Override
	public String toString() {
		return this.getText();
	}

	@Override
	public long[] getContributors() {
		return null;
	}

	@Override
	public int getAccessLevel() {
		return 0;
	}

	@Override
	public RateLimitStatus getRateLimitStatus() {
		return null;
	}

	@Override
	public ExtendedMediaEntity[] getExtendedMediaEntities() {
		return null;
	}

	@Override
	public MediaEntity[] getMediaEntities() {
		return null;
	}

	@Override
	public SymbolEntity[] getSymbolEntities() {
		return null;
	}

	@Override
	public URLEntity[] getURLEntities() {
		return null;
	}

	@Override
	public UserMentionEntity[] getUserMentionEntities() {
		return null;
	}

	@Override
	public long getCurrentUserRetweetId() {
		return 0;
	}

	@Override
	public int getFavoriteCount() {
		return 0;
	}

	@Override
	public GeoLocation getGeoLocation() {
		return null;
	}

	@Override
	public String getInReplyToScreenName() {
		return null;
	}

	@Override
	public long getInReplyToStatusId() {
		return 0;
	}

	@Override
	public long getInReplyToUserId() {
		return 0;
	}

	@Override
	public Place getPlace() {
		return null;
	}

	@Override
	public Status getQuotedStatus() {
		return null;
	}

	@Override
	public long getQuotedStatusId() {
		return 0;
	}

	@Override
	public Status getRetweetedStatus() {
		return null;
	}

	@Override
	public Scopes getScopes() {
		return null;
	}

	@Override
	public String getSource() {
		return null;
	}

	@Override
	public String[] getWithheldInCountries() {
		return null;
	}

	@Override
	public boolean isFavorited() {
		return false;
	}

	@Override
	public boolean isPossiblySensitive() {
		return false;
	}

	@Override
	public boolean isRetweet() {
		return false;
	}

	@Override
	public boolean isRetweeted() {
		return false;
	}

	@Override
	public boolean isRetweetedByMe() {
		return false;
	}

	@Override
	public boolean isTruncated() {
		return false;
	}

}
