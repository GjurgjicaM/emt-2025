create materialized view accommodations_per_hosts as
select h.id         as host_id,
       count(a.id)  as num_accommodations
from host h
    left join
accommodation a on a.host_id = h.id
group by h  .id;

create materialized view hosts_per_countries as
select c.id         as country_id,
       count(h.id)  as num_hosts
from country c
         left join
     host h on h.country_id = c.id
group by c.id