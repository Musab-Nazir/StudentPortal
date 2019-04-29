DROP TABLE IF EXISTS programs;
DROP TABLE IF EXISTS schools;

CREATE TABLE IF NOT EXISTS schools (
    SchoolCode	 		VARCHAR(2) NOT NULL,
    SchoolDescription   VARCHAR(64) NOT NULL,
	CONSTRAINT PK_school_ID PRIMARY KEY (SchoolCode)
);

CREATE TABLE IF NOT EXISTS programs (
    ProgramCode VARCHAR(8) PRIMARY KEY,
    ProgramDescription VARCHAR(64) NOT NULL,
    SchoolCode VARCHAR(2) NOT NULL,
    DiplomaCode VARCHAR(8) NOT NULL,
	CONSTRAINT FK_schoolCode FOREIGN KEY (SchoolCode) REFERENCES schools (SchoolCode)
);

INSERT INTO "schools" VALUES
    ('SB','School of Business'),
    ('SH','School of Hlth & Comm Services'),
    ('SK','School of Justice & Emerg Serv'),
    ('SM','School of Media, Art & Design'),
    ('SN','SchSkill Trd, Appr &Renew Tech'),
    ('SO','School College Work Initiative'),
    ('SQ','Sch of Science & Engineer Tech'),
    ('SV','Centre for Food'),
    ('SX','School of Bus, IT & Management'),
    ('SY','Sch Interdisciplinary Studies');
	
INSERT INTO "programs" VALUES
    ('AAGR','Food and Farming','SV','2YR'),
    ('ACAR','Animal Care','SH','1YR'),
    ('ACCT','Business - Accounting','SX','2YR'),
    ('ACGR','Activation Coor in Gerontology','SH','GRADC'),
    ('ACPA','Accounting and Payroll','SX','2YR'),
    ('ACTU','Bus Acct - UOIT Transfer','SX','2YR'),
    ('ADA','Animation - Digital Arts','SM','2YR'),
    ('ADFL','Advanced Filmmaking','SM','GRADC'),
    ('ADIG','Animation - Digital','SM','2YR'),
    ('ADMH','Addictions and Mental Health','SH','GRADC'),
    ('ADMM','Advert-Digital Media Managemen','SM','GRADC'),
    ('ADP','Animation - Digital Production','SM','3YR'),
    ('ARHC','Architectural Technician','SQ','2YR'),
    ('ARHY','Architectural Technology','SQ','3YR'),
    ('AVMC','Advertising and Marketing Comm','SM','2YR'),
    ('BACT','Bus Admin - Accounting','SX','3YR'),
    ('BDCT','Building Const Technician','SN','2YR'),
    ('BESB','Entrepreneurship & Small Bus','SX','2YR'),
    ('BETU','Entre Small Bus UOIT Transfer','SX','2YR'),
    ('BFNC','Bus Admin - Finance','SX','3YR'),
    ('BFND','Business Fundamentals','SX','1YR'),
    ('BGEN','Bus Admin - General','SB','3YR'),
    ('BHRM','Bus Admin - Human Resources','SX','3YR'),
    ('BITY','Biotechnology - Advanced','SQ','3YR'),
    ('BMKG','Bus Admin - Marketing','SX','3YR'),
    ('BMOP','Bus Admin-Materials and Oper M','SX','3YR'),
    ('BMTY','Biomedical Eng. Technology','SQ','3YR'),
    ('BMYF','Biomedical Eng Technology FT','SQ','3YR'),
    ('BRCM','Broad-Radio and Contemp Media','SM','2YR'),
    ('BSOC','Bus Admin-Sup Chain & Op Ma Co','SX','3YR'),
    ('BSOM','Bus Admin-Sup Chain and Op Ma','SX','3YR'),
    ('BTYF','Biotechnology - Fast track','SQ','3YR'),
    ('CCH','Reg Nurse Critical Care Nrsg','SH','GRADC'),
    ('CCSF','Comm Ser and Child Stud Found','SH','1YR'),
    ('CCST','Constr Carpentry - Sustainable','SN','2YR'),
    ('CDA','Communicative Disorders Assist','SH','GRADC'),
    ('CETC','Civil Engineering Technician','SQ','2YR'),
    ('CETY','Civil Engineering Technology','SQ','3YR'),
    ('CFND','Computer Foundations','SX','1YR'),
    ('CHEM','Chemical Eng. Technology','SQ','3YR'),
    ('CHLF','Chemical Lab Tech - Fast Track','SQ','3YR'),
    ('CHLT','Chemical Lab Technologist','SQ','3YR'),
    ('CHMF','Chemical Eng Tech - Fast track','SQ','3YR'),
    ('CICE','Community Integration Coop Ed','SH','CERT2'),
    ('CLBT','Chemical Laboratory Technician','SQ','2YR'),
    ('CMGT','Culinary Management','SV','2YR'),
    ('COHT','Constr and Hoisting Techniques','SN','1YR'),
    ('CORC','Crane Oper, Rigg and Const Tec','SN','1YR'),
    ('CPA','Computer Programmer Analyst','SX','3YR'),
    ('CPGM','Computer Programmer','SX','2YR'),
    ('CSK','Culinary Skills','SV','1YR'),
    ('CSTC','Computer Systems Technician','SX','2YR'),
    ('CSTU','Computer System Tech - UOIT','SX','2YR'),
    ('CSTY','Computer Systems Technology','SX','3YR'),
    ('CTA','Paralegal','SK','2YR'),
    ('CTAP','Para legal Grad Cert','SK','GRADC'),
    ('CTCC','Civil Eng Technician Co-op','SQ','2YR'),
    ('CTMG','Cosmetic Techniques and Mgmt','SX','2YR'),
    ('CTSS','Court Support Services','SK','1YR'),
    ('CTYC','Civil Eng Technology Co-op','SQ','3YR'),
    ('CWBD','Contemporary Web Design','SM','2YR'),
    ('CYCA','Child and Youth Care','SH','3YR'),
    ('CYCB','Child and Youth Care - Brock','SH','3YR'),
    ('DAII','Dental Assisting (Levels I&II)','SH','1YR'),
    ('DATA','Data Analytics for Bus Dec Mak','SX','GRADC'),
    ('DCSB','Durham Catholic School Board','SO','NOCERT'),
    ('DDSB','Durham District School Board','SO','NOCERT'),
    ('DENT','Dental Hygiene','SH','3YR'),
    ('DGPH','Digital Photography','SM','2YR'),
    ('DGVP','Digital Video Production','SM','2YR'),
    ('DRA','Dental Reception and Admin','SH','1YR'),
    ('DSWK','Developmental Services Worker','SH','2YR'),
    ('ECE','Early Childhood Education','SH','2YR'),
    ('EETN','Electrical Engineer Technician','SN','2YR'),
    ('ELEC','Electrical Techniques','SN','1YR'),
    ('ELTC','Electronics Eng. Technician','SQ','2YR'),
    ('ELTY','Electronics Eng. Technology','SQ','3YR'),
    ('ELYF','Electronics Eng. Tech - Fast T','SQ','3YR'),
    ('EMCC','9-1-1 Emerg & Call Centre Comm','SK','2YR'),
    ('EMSF','Emergency Services Fundamental','SK','1YR'),
    ('EMTY','Electro-Mechanical Engin Tech','SQ','3YR'),
    ('ENVF','Environmental Tech -Fast track','SQ','3YR'),
    ('ENVT','Environmental Technology','SQ','3YR'),
    ('ESMG','Esthetician - Spa Management','SX','2YR'),
    ('FAD','Foundations in Art and Design','SM','1YR'),
    ('FINC','Business - Finance','SX','2YR'),
    ('FINE','Fine Arts - Advanced','SM','3YR'),
    ('FIT','Fitness and Health Promotion','SH','2YR'),
    ('FLSC','Fire & Life Safety Sys Tech Co','SK','2YR'),
    ('FLST','Fire and Life Safety Sys Tech','SK','2YR'),
    ('GART','Game - Art','SM','3YR'),
    ('GASA','General Arts & Science - Trent','SY','1YR'),
    ('GASB','General Arts & Science - Bus','SY','1YR'),
    ('GASC','General Arts & Science','SY','1YR'),
    ('GASF','Gen Art & Sci - UOIT Foren Psy','SY','1YR'),
    ('GASK','Gen Art & Sci - Sci & Eng Coll','SY','1YR'),
    ('GASS','Gen Art & Sci - Student Succes','SY','1YR'),
    ('GAST','Gen Art & Sci - UOIT Lib Arts','SY','1YR'),
    ('GASZ','Gen Art & Sci - UOIT Sci & Eng','SY','1YR'),
    ('GDES','Graphic Design','SM','3YR'),
    ('GDEV','Game Development','SM','3YR'),
    ('GFIT','Gas Technician 2','SN','1YR'),
    ('HORT','Horticulture Technician','SV','2YR'),
    ('HRM','Business - Human Resources','SX','2YR'),
    ('HROC','Hosp-Hotel+Rest Op Mgmt Co-Op','SV','2YR'),
    ('HROM','Hosp-Hotl and Rest Oper Manage','SV','2YR'),
    ('HRTU','Business-Human Res UOIT Tran','SX','2YR'),
    ('HSKL','Hospitality Skills','SV','1YR'),
    ('HURM','Human Resources Management','SX','GRADC'),
    ('HVAC','Heat Vent & Air Cond Tech','SN','1YR'),
    ('IMDE','Interactive Media Design','SM','2YR'),
    ('INTB','International Business Managem','SX','GRADC'),
    ('ISCN','Info Sys Security-Comp&Network','SX','GRADC'),
    ('JOBM','Journal-Broad and Elect Media','SM','2YR'),
    ('JOMM','Journalism - Mass Media','SM','2YR'),
    ('KPRB','Kawartha Pineridge Sch Board','SO','NOCERT'),
    ('LAW','Adv Law Enforce and Invest','SK','GRADC'),
    ('LCAD','Law Clerk Advanced','SK','3YR'),
    ('LCAF','Law Clerk Adv - Fast Track','SK','3YR'),
    ('LIBT','Library and Infor Technician','SX','2YR'),
    ('MADR','Mediation/Alt Dispute Resolut','SK','GRADC'),
    ('MAST','Massage Therapy','SH','3YR'),
    ('MBAD','Music Business Administration','SM','2YR'),
    ('MBUS','Music Business Management','SM','3YR'),
    ('METC','Mechanical Eng. Technician','SQ','2YR'),
    ('METY','Mechanical Eng. Technology','SQ','3YR'),
    ('MFUN','Media Fundamentals','SM','1YR'),
    ('MKTU','Business - Marketing UOIT Tran','SX','2YR'),
    ('MPLU','Mechanical Tech - Plumbing','SN','1YR'),
    ('MPTN','Motive Power Techn Service&Mgt','SN','2YR'),
    ('MRKG','Business - Marketing','SX','2YR'),
    ('MTED','Mech Tech - Elevating Devices','SN','2YR'),
    ('MTMW','Mechanical Tec -Millwright','SN','2YR'),
    ('NDE','Mechanical Eng. Techn. - NDE','SQ','2YR'),
    ('NDEF','Mec Eng Non-Dest Fast Track','SQ','2YR'),
    ('OFAD','Office Administration','SX','1YR'),
    ('OFCC','Office Admin - Legal Co-Op','SK','2YR'),
    ('OFEX','Office Admin - Executive','SX','2YR'),
    ('OFHF','Office Ad -Health (Fast Track)','SX','2YR'),
    ('OFHS','Office Admin - Health Services','SX','2YR'),
    ('OFLG','Office Admin - Legal','SK','2YR'),
    ('OFRE','Office Admin. - Real Estate','SK','2YR'),
    ('OPER','Business - Operations','SX','2YR'),
    ('OTPA','Occ Ther Assist&Physio Assist','SH','2YR'),
    ('PADV','Paramedic - Advanced Care','SK','GRADC'),
    ('PETN','Power Engineering Techn - 4th','SN','1YR'),
    ('PFET','Pre-serv Firefighter - Ed & Tr','SK','1YR'),
    ('PHGR','Photography','SM','2YR'),
    ('PHMF','Pharm & Food Sci - Fast track','SQ','3YR'),
    ('PHRM','Pharmaceutical & Food Sci Tech','SQ','3YR'),
    ('PHSC','Pre-Health Science - College','SY','1YR'),
    ('PHSO','Pre-Health Science - Online','SY','1YR'),
    ('PHSU','Pre-Health Science-University','SY','1YR'),
    ('PNFL','Practical Nursing - Flex','SH','2YR'),
    ('PNIE','Practical Nurse -Intl Educated','SH','2YR'),
    ('PNII','Practical Nursing','SH','2YR'),
    ('POFD','Police Foundations','SK','2YR'),
    ('PPC','Paramedic','SK','2YR'),
    ('PROM','Project Management','SX','GRADC'),
    ('PSI','Protection, Sec and Investigat','SK','2YR'),
    ('PSWK','Personal Support Worker','SH','1YR'),
    ('PUBL','Public Relations','SM','3YR'),
    ('PVNC','Peterborough Catholic School B','SO','NOCERT'),
    ('RECL','Recreation and Leisure Service','SX','2YR'),
    ('SBMT','Sport Business Management','SX','GRADC'),
    ('SCOP','Bus-Supply Chain and Operation','SX','2YR'),
    ('SCTU','Bus-Sup Chain and Oper- UOIT','SX','2YR'),
    ('SEMC','Special Events Mgmt Co-Op','SV','2YR'),
    ('SEMT','Special Events Management','SV','2YR'),
    ('SPAD','Sport Administration','SX','2YR'),
    ('SPMN','Sport Management','SX','3YR'),
    ('SSWK','Social Service Worker','SH','2YR'),
    ('TRDE','Trades Fundamentals','SN','1YR'),
    ('VICT','Victimology','SK','GRADC'),
    ('VIPR','Video Production','SM','2YR'),
    ('WATR','Water Quality Technician','SQ','2YR'),
    ('WELD','Welding Techniques','SN','1YR'),
    ('WETC','Welding Eng Technician Co-op','SN','2YR'),
    ('WETN','Welding Engineering Technician','SN','2YR'),
    ('WTRC','Water Quality Technician Co-op','SQ','2YR'),
    ('YJI','Youth Justice and Intervention','SK','GRADC');