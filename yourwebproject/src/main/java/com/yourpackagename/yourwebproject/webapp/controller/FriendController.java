/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yourpackagename.yourwebproject.webapp.controller;

import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Lists.newArrayList;
import static org.apache.commons.lang.StringUtils.startsWithIgnoreCase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.yourpackagename.framework.web.datatable.PagingCriteria;
import com.yourpackagename.framework.web.datatable.ResultSet;
import com.yourpackagename.framework.web.datatable.TableParam;
import com.yourpackagename.framework.web.datatable.WebResultSet;
import com.yourpackagename.framework.web.webflow.Flow;
import com.yourpackagename.yourwebproject.model.entity.Account;
import com.yourpackagename.yourwebproject.model.entity.Customer;
import com.yourpackagename.yourwebproject.service.FriendService;
import com.yourpackagename.yourwebproject.webapp.utils.ControllerUtils;

/**
 * @author: Martin [ytoh] Hvizdos (martin <dot> hvizdos <at> testile <dot> org)
 */
@Controller
public class FriendController {

    @Autowired
    private FriendService friendService;

    @RequestMapping("/listMyFiends")
    public @ResponseBody List<String> listFriends(@Flow Account userAccount, @RequestParam("term") final String query) {
        return newArrayList(filter(friendService.userFriends(userAccount.getUserName()), new Predicate<String>() {

            @Override
            public boolean apply(String input) {
                return startsWithIgnoreCase(input, query);
            }
        }));
    }
    
    @RequestMapping(value="/get", method=RequestMethod.GET)
    public @ResponseBody WebResultSet<Customer> getCustomers(@Flow Account userAccount,@TableParam PagingCriteria criteria)
    {
    	List<Customer> customers = ImmutableList.<Customer>of( new Customer(Long.valueOf(1), "prueba"));
    	ResultSet<Customer> customersResultSet = new ResultSet<Customer>(customers, 
    			Long.valueOf(1), Long.valueOf(1));//this.customerService.getCustomers(criteria);
    	return ControllerUtils.getWebResultSet(criteria, customersResultSet);
    }

}
