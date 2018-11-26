/*******************************************************************************
 * @rrs_start_copyright
 * 
 * Copyright 2005 (C) Red Rabbit Software Inc. All rights reserved. This
 * software is the confidential and proprietary information of Red Rabbit
 * Software, Inc. ("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with the terms
 * of the license agreement you entered into with Red Rabbit Software.
 * 
 * 
 * @rrs_end_copyright
 ******************************************************************************/
/*******************************************************************************
 * @rrs_start_disclaimer
 * 
 * The contents of this file are subject to the Red Rabbit Software Inc. Corona
 * License ("License"); You may not use this file except in compliance with the
 * License. THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO
 * EVENT SHALL THE RED RABBIT SOFTWARE INC. OR ITS CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 * @rrs_end_disclaimer
 ******************************************************************************/

package com.rrs.corona.solutionsacceleratorstudio.datasource;

/**
 * 
 * @author Debadatta Mishra
 * 
 */

public class DbmsInfo
{
	/**
	 * Database URL
	 */
	String	dbUrl;			// for database url
	/**
	 * Database USER
	 */
	String	dbUser;			// for database USER
	/**
	 * Database Password
	 */
	String	dbPassword;		// for database password
	/**
	 * DataSource Name
	 */
	String	dataSourceName;	// Logical dataSource name

	/**
	 * @return Returns the password.
	 */
	public String getDbPassword( )
	{
		return dbPassword;
	}

	/**
	 * @param pwd
	 *            The password to set.
	 */
	public void setDbPassword( String pwd )
	{
		this.dbPassword = pwd;
	}

	/**
	 * @return Returns the url.
	 */
	public String getDbUrl( )
	{
		return dbUrl;
	}

	/**
	 * @param url
	 *            The url to set.
	 */
	public void setDbUrl( String url )
	{
		this.dbUrl = url;
	}

	/**
	 * @return Returns the user.
	 */
	public String getDbUser( )
	{
		return dbUser;
	}

	/**
	 * @param user
	 *            The user to set.
	 */
	public void setDbUser( String user )
	{
		this.dbUser = user;
	}

	/**
	 * @return Returns the dataSourceName.
	 */
	public String getDataSourceName( )
	{
		return dataSourceName;
	}

	/**
	 * @param dataSourceName
	 *            The dataSourceName to set.
	 */
	public void setDataSourceName( String dataSourceName )
	{
		this.dataSourceName = dataSourceName;
	}

}
