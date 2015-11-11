--
-- MySQL 5.6.12
-- Thu, 11 Sep 2014 15:38:54 +0000
--

CREATE TABLE `users` (
   `uid` bigint(20) not null auto_increment,
   `name` text,
   `email` text,
   `branch` text,
   `college` text,
   `added_on` timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
   PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;


CREATE TABLE `college` (
   `cid` bigint(20) not null auto_increment,
   `name` text,
   `address1` text,
   `address2` text,
   `city` text,
   `state` text,
   `country` text,
   `lat` double,
   `lon` double,
   `contact_num` text,
   `email` text,
   `added_by` bigint(20),
   `added_on` timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
   `active` int(11),
   PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;


CREATE TABLE `college_branch` (
   `id` bigint(20) not null auto_increment,
   `cid` int(11),
   `bid` int(11),
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;


CREATE TABLE `events` (
   `eid` bigint(20) not null auto_increment,
   `pcid` int(11),
   `uid` bigint(20),
   `head` text,
   `body` text,
   `pic` text,
   `start_time` timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
   `end_time` timestamp not null default '0000-00-00 00:00:00',
   `added_on` timestamp not null default '0000-00-00 00:00:00',
   PRIMARY KEY (`eid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;


CREATE TABLE `item_bid_data` (
   `id` bigint(20) not null auto_increment,
   `iid` bigint(20),
   `uid` bigint(20),
   `price` double,
   `added_on` timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
   `status` int(11),
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;


CREATE TABLE `item_data` (
   `bid` bigint(20) not null auto_increment,
   `uid` bigint(20),
   `uid_cust` bigint(20),
   `name` text,
   `details` text,
   `pic` text,
   `offer_price` double,
   `sell_price` double,
   `bid_date` timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
   `status` int(11),
   `added_on` timestamp not null default '0000-00-00 00:00:00',
   PRIMARY KEY (`bid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;


CREATE TABLE `post` (
   `pid` bigint(20) not null auto_increment,
   `pcid` int(11),
   `uid` bigint(20),
   `head` text,
   `body` text,
   `pic` text,
   `added_on` timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
   `spams` int(11) default '0',
   `active` int(11) default '0',
   `pinned` int(11) default '0',
   `priority` int(11) default '1',
   PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;


CREATE TABLE `post_category` (
   `pcid` int(11) not null auto_increment,
   `name` text,
   PRIMARY KEY (`pcid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;


CREATE TABLE `user_branch` (
   `bid` int(11) not null auto_increment,
   `name` text,
   PRIMARY KEY (`bid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;