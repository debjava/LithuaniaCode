#!/bin/sh -x
j2eeadmin -addJmsFactory jms/RemoteTCF topic -props url=corbaname:iiop:$1:1050#$1

