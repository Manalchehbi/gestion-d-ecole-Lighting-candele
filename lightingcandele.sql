-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : dim. 15 jan. 2023 à 12:21
-- Version du serveur : 10.4.27-MariaDB
-- Version de PHP : 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `lightingcandele`
--

-- --------------------------------------------------------

--
-- Structure de la table `eleve`
--

CREATE TABLE `eleve` (
  `numero` int(10) NOT NULL,
  `nom` varchar(20) NOT NULL,
  `prenom` varchar(20) NOT NULL,
  `age` int(11) NOT NULL DEFAULT 0,
  `niveau` varchar(20) DEFAULT NULL,
  `date_inscription` date NOT NULL,
  `nb_matier` varchar(20) DEFAULT '-1',
  `transport` varchar(4) DEFAULT NULL,
  `montant` int(11) NOT NULL,
  `type_eleve` int(11) NOT NULL,
  `action` varchar(2) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `eleve`
--

INSERT INTO `eleve` (`numero`, `nom`, `prenom`, `age`, `niveau`, `date_inscription`, `nb_matier`, `transport`, `montant`, `type_eleve`, `action`) VALUES
(4, 'bra', 'ggggg', 123, NULL, '2023-01-06', '-1', 'Oui', 200, 0, ''),
(10, 'zeze', 'ezezeez', 0, '3ème année', '2023-01-11', '3 matières', 'Oui', 370, 2, ''),
(14, 'dsd', 'sdsd', 0, '3ème année', '2023-01-11', '3 matières', NULL, 300, 3, ''),
(15, 'dsddsds', 'sdssdsdd', 0, '5ème année', '2023-01-11', '3 matières', 'Oui', 370, 3, ''),
(18, 'chehbi', 'adam', 0, '3ème année', '2023-01-12', '-1', 'Oui', 200, 1, ''),
(19, 'chehbi', 'manal', 12, NULL, '2023-01-12', '-1', 'Oui', 200, 0, ''),
(20, 'ZERIOUH', 'meriem', 4, NULL, '2023-01-19', '-1', 'Non', 100, 0, ''),
(23, 'dsdfs', 'dsfsd', 0, '3ème année', '2023-01-19', '-1', 'Oui', 200, 1, ''),
(24, 'sd2éé', 'ééed', 0, '1ère anne', '2023-01-11', '-1', NULL, 125, 1, ''),
(25, 'aaaa', 'zzzzz', 0, '4ème année', '2023-01-10', '3 matières', 'Oui', 370, 3, ''),
(26, 'eze', 'ezez', 0, '2ème année', '2023-01-16', '2 matières', 'Oui', 260, 2, ''),
(27, 'eez', 'ezez', 11, NULL, '2023-01-19', '-1', 'Non', 100, 0, '');

-- --------------------------------------------------------

--
-- Structure de la table `payement`
--

CREATE TABLE `payement` (
  `id` int(10) NOT NULL,
  `id_eleve` int(10) NOT NULL,
  `mois_paye` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `payement`
--

INSERT INTO `payement` (`id`, `id_eleve`, `mois_paye`) VALUES
(1, 18, 10),
(7, 4, 5),
(10, 20, 6),
(14, 27, 1);

-- --------------------------------------------------------

--
-- Structure de la table `professeur`
--

CREATE TABLE `professeur` (
  `id` int(11) NOT NULL,
  `nom` varchar(11) NOT NULL,
  `prenom` varchar(11) NOT NULL,
  `date_inscription` date NOT NULL,
  `nb_matieres` varchar(30) NOT NULL,
  `salaire` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `professeur`
--

INSERT INTO `professeur` (`id`, `nom`, `prenom`, `date_inscription`, `nb_matieres`, `salaire`) VALUES
(1, 'CHEHBI', 'manal', '2023-01-09', '2', 1234),
(2, 'CHEHBI', 'manal', '2023-01-04', '2', 1234),
(3, 'CHEHBI', 'manal', '2023-01-04', '2', 1234),
(4, 'CHEHBI', 'manal', '2023-01-04', '3 matières', 1234),
(6, 'zeriouh', 'Adam', '2023-01-04', '4', 1500),
(7, 'belaide', 'rajae', '2023-01-04', '3 matières', 1700),
(8, 'errazi', 'fatima', '2023-01-04', '3', 1300),
(9, 'bojdour', 'amal', '2023-01-04', '3 matières', 2100),
(10, 'boutalamin', 'fatoma', '2023-01-14', '2 matières', 1234),
(12, 'azrour', 'mourad', '2023-01-11', '3 matières', 12344);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `eleve`
--
ALTER TABLE `eleve`
  ADD PRIMARY KEY (`numero`);

--
-- Index pour la table `payement`
--
ALTER TABLE `payement`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_eleve` (`id_eleve`);

--
-- Index pour la table `professeur`
--
ALTER TABLE `professeur`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `eleve`
--
ALTER TABLE `eleve`
  MODIFY `numero` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT pour la table `payement`
--
ALTER TABLE `payement`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT pour la table `professeur`
--
ALTER TABLE `professeur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `payement`
--
ALTER TABLE `payement`
  ADD CONSTRAINT `payement_ibfk_1` FOREIGN KEY (`id_eleve`) REFERENCES `eleve` (`numero`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
