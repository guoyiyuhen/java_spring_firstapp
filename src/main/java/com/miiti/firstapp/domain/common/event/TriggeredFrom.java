package com.miiti.firstapp.domain.common.event;

import com.miiti.firstapp.utils.IpAddress;

public interface TriggeredFrom {

  /**
   * Get the IP address where the request originated from
   *
   * @return an IP address
   */
  IpAddress getIpAddress();
}
