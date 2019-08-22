FROM websphere-liberty:webProfile7
ADD ./WAR/Transfer.war /config/dropins/
