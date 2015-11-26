SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `TEConozco`
--

-- --------------------------------------------------------

--
-- Table structure for table `personaje`
--

CREATE TABLE `personaje` (
  `id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `pregunta` varchar(500) NOT NULL,
  `pregunta_anterior` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
