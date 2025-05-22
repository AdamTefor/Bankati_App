<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Mes Demandes de Crédit - Bankati</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #f2f4f8;
            margin: 0;
            padding: 30px;
        }

        h2 {
            color: #3f51b5;
            text-align: center;
            margin-bottom: 25px;
        }

        .top-bar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 30px;
        }

        .left-actions {
            display: flex;
            gap: 10px;
        }

        .btn-pdf, .btn-ajouter, .btn-retour, .btn-deconnexion {
            display: inline-block;
            padding: 10px 18px;
            font-weight: bold;
            text-decoration: none;
            border-radius: 6px;
            color: white;
        }

        .btn-pdf { background-color: #4CAF50; }
        .btn-ajouter { background-color: #2196F3; }
        .btn-retour { background-color: #607d8b; }
        .btn-deconnexion { background-color: #e53935; }

        .btn-pdf:hover { background-color: #388E3C; }
        .btn-ajouter:hover { background-color: #1976D2; }
        .btn-retour:hover { background-color: #455a64; }
        .btn-deconnexion:hover { background-color: #c62828; }

        table {
            width: 100%;
            border-collapse: collapse;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }

        th, td {
            padding: 12px 16px;
            border-bottom: 1px solid #ccc;
            text-align: center;
        }

        th {
            background-color: #3f51b5;
            color: white;
        }

        tr:last-child td {
            border-bottom: none;
        }

        .btn-delete {
            background-color: #e53935;
            color: white;
            padding: 6px 10px;
            text-decoration: none;
            border-radius: 5px;
            font-size: 13px;
        }

        .btn-delete:hover {
            background-color: #c62828;
        }

        .etat {
            font-weight: bold;
            text-transform: uppercase;
        }

        .etat.EN_ATTENTE { color: orange; }
        .etat.APPROUVÉE { color: green; }
        .etat.REFUSÉE { color: red; }
    </style>
</head>
<body>

<div class="top-bar">
    <div class="left-actions">
        <a href="${pageContext.request.contextPath}/credit/export/pdf" class="btn-pdf">📄 Exporter en PDF</a>
        <a href="${pageContext.request.contextPath}/credit/add" class="btn-ajouter">➕ Nouvelle demande</a>
        <a href="${pageContext.request.contextPath}/client/dashboard" class="btn-retour">← Retour à l’accueil</a>
    </div>
    <a href="${pageContext.request.contextPath}/views/login.jsp" class="btn-deconnexion">🚪 Se déconnecter</a>
</div>

<h2>Mes Demandes de Crédit</h2>

<c:if test="${empty demandes}">
    <p style="text-align:center;">Vous n’avez encore effectué aucune demande de crédit.</p>
</c:if>

<c:if test="${not empty demandes}">
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Montant (DH)</th>
            <th>Statut</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="demande" items="${demandes}">
            <tr>
                <td>${demande.id}</td>
                <td><fmt:formatNumber value="${demande.montant}" type="number" minFractionDigits="2"/></td>
                <td class="etat ${demande.statut}">${demande.statut}</td>
                <td>
                    <c:if test="${demande.statut == 'EN_ATTENTE'}">
                        <a href="${pageContext.request.contextPath}/credit/delete?id=${demande.id}" class="btn-delete">Supprimer</a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

</body>
</html>
