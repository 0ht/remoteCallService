FROM websphere-liberty:webProfile7
ADD ./WAR/remoteCallService.war /config/dropins/
