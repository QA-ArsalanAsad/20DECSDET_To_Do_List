# 20DECSDET_To_Do_List
Coverage 85%


Description: The following repostiory details the steps taken into creating a To-Do-List application which will take in user input and store that into a database. **Note** This project contains two entities a Gym and Equipment, the relatioinship can be seen in the documentation as an ERD diagram.

##Getting Started
The following will detail how to run this project on your machine.

##Requirements
To run this application you will need:
```

-Java JDK 11 or higher
-Git
-Spring
-Visual Studio Code
-MySQL
-Maven

```

##Install

First the required applications above need to be installed. To run the tests as your own on your local machine you will have a war file downloaded to your system once this repo is cloned. In your command line type the following;
```
java -jar 20DECSDET-TDL.war

```
##Running Test

You must first fork this repo and clone the repo in a GitBash using your URL. Once that is done you will be able to run it on your local machine.

To run the test, once this repo is forked and cloned you will be able to run it through your IDE.

##Unit Tests
The following will detail some of the tests I have covered to show the interactions between my entities and if the codes implemented worked.

```

GYM ENTITY


	@Test
	void testCreate() throws Exception {
		when(this.srvc.create(T_GYM_1)).thenReturn(this.mapToDTO(T_GYM_1));
		assertThat(new ResponseEntity<GymDTO>(this.mapToDTO(T_GYM_1), HttpStatus.CREATED))
				.isEqualTo(this.controller.create(T_GYM_1));
		verify(this.srvc, Mockito.times(1)).create(T_GYM_1);

	}

	@Test
	void testReadAll() throws Exception {
		List<GymDTO> gymDTOs = listGym.stream().map(this::mapToDTO).collect(Collectors.toList());
		when(this.srvc.readAll()).thenReturn(gymDTOs);
		assertThat(this.controller.readAll()).isEqualTo(new ResponseEntity<>(gymDTOs, HttpStatus.OK));
		verify(this.srvc, atLeastOnce()).readAll();

	}

	@Test
	void testReadLatest() throws Exception {
		final Long id = 2L;
		when(this.srvc.readLatest(id)).thenReturn(this.mapToDTO(T_GYM_2));
		assertThat(new ResponseEntity<GymDTO>(this.mapToDTO(T_GYM_2), HttpStatus.OK))
				.isEqualTo(this.controller.readLatest(id));
		verify(this.srvc, atLeastOnce()).readLatest(id);
	}
  
  
  EQUIPMENT ENTITY 
  
  @Test
	void testCreate() throws Exception {
		when(this.eSrvc.create(T_E_1)).thenReturn(this.mapToDTO(T_E_1));
		assertThat(new ResponseEntity<EquipmentDTO>(this.mapToDTO(T_E_1), HttpStatus.CREATED))
				.isEqualTo(this.eController.create(T_E_1));
		verify(this.eSrvc, atLeastOnce()).create(T_E_1);

	}

	@Test
	void testReadAll() throws Exception {
		List<EquipmentDTO> eDTOs = listEquipment.stream().map(this::mapToDTO).collect(Collectors.toList());
		when(this.eSrvc.readAll()).thenReturn(eDTOs);
		assertThat(this.eController.readAll()).isEqualTo(new ResponseEntity<>(eDTOs, HttpStatus.OK));
		verify(this.eSrvc, atLeastOnce()).readAll();
	}

	@Test
	void testReadLatest() throws Exception {
		final Long id = 2L;
		when(this.eSrvc.readLatest(id)).thenReturn(this.mapToDTO(T_E_2));
		assertThat(this.eController.readLatest(id))
				.isEqualTo(new ResponseEntity<EquipmentDTO>(this.mapToDTO(T_E_2), HttpStatus.OK));
		verify(this.eSrvc, atLeastOnce()).readLatest(id);
	}


```

##Deploy

To run this project and use it in conjunction with a database. You must have MySQL installed and running.

Open a new file in your src/test/resources and name it schema.sql and another called data.sql and input the following:

```
SCHEMA
DROP TABLE IF EXISTS `gym` CASCADE;
DROP TABLE IF EXISTS `equipment` CASCADE;
create table gym (id bigint PRIMARY KEY AUTO_INCREMENT, name varchar(255) not null, type varchar(255) not null);
create table equipment (id bigint PRIMARY KEY AUTO_INCREMENT, price integer not null, type varchar(255) not null, gym_id bigint);

DATA

insert into gym (name, type) values ('Manchester', 'GymWhale');
insert into gym (name, type) values ('Birmingham', 'JDBCGym');
insert into gym (name, type) values ('London', 'CleanGym');
insert into equipment(type, price, gym_id) values ('Dumbbells', 30, 1);
insert into equipment(type, price, gym_id) values ('Bench', 90, 1);
insert into equipment(type, price, gym_id) values ('Barbell', 40, 1);

```

## Built with
Maven, dependency injection - https://maven.apache.org/

##Authors
Arsalan Asad

##License

This project is licensed under the MIT license, see the [LICENSE.md](LICENSE.md) file for details.

##Acknowledgements

I would like to thank the following trainers for the guidance supplied during this project;
-Alan Davis
-Vinesh Ghela
-Reece Elder
-Savanna Vaithilingam
