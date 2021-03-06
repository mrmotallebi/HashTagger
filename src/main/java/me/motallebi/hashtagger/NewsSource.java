/**
 * 
 */
package me.motallebi.hashtagger;

import java.io.OutputStream;
import java.util.Date;

/**
 * @author mrmotallebi
 *
 */
public interface NewsSource extends Iterable<NewsArticle> {

	public void loadNews();

	public void loadNews(Date date);

	public void loadNews(Integer count);

	public void loadNews(Date date, Integer count);

	public OutputStream getStream();

}
