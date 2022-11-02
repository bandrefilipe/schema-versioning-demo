insert into `user` (id, schema, username, email) values
 ('a6a6d95f-f077-44d1-b824-8161da322945', null, 'b_andrefilipe91', 'b.andrefilipe@email.com')
,('d117c043-a2ed-412b-a20d-8f811d9ac026', 1,    'jack_doe',        'jack.doe@email.com')
,('60ee766a-5a59-11ed-9b6a-0242ac120002', 2,    'jane_doe',        null)
;

insert into `user_contacts` (user_id, contact, contact_type) values
 ('60ee766a-5a59-11ed-9b6a-0242ac120002', 'jane_doe@email.com', 'EMAIL')
,('60ee766a-5a59-11ed-9b6a-0242ac120002', 'jdoe13@email.com', 'EMAIL')
,('60ee766a-5a59-11ed-9b6a-0242ac120002', '718-316-8909', 'PHONE')
;
