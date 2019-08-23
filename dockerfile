FROM websphere-liberty:webProfile7
ADD ./WAR/transfer.war /config/dropins/
