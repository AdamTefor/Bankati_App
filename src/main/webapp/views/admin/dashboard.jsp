<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Tableau de Bord - Administrateur - Bankati</title>
    <style>
        body {
            margin: 0;
            font-family: 'Segoe UI', sans-serif;
            background-color: #f4f6f8;
        }

        .header {
            background-color: #3f51b5;
            color: white;
            padding: 20px;
            text-align: center;
        }

        .header h1 {
            margin: 0;
        }

        .container {
            max-width: 1000px;
            margin: 40px auto;
            padding: 30px;
            background-color: white;
            border-radius: 10px;
            box-shadow: 0 0 12px rgba(0,0,0,0.1);
        }

        .section {
            margin-bottom: 30px;
        }

        .section h2 {
            color: #3f51b5;
            border-bottom: 2px solid #eee;
            padding-bottom: 10px;
            margin-bottom: 20px;
        }

        .btn {
            display: inline-block;
            margin: 10px 10px 10px 0;
            padding: 14px 20px;
            background-color: #3f51b5;
            color: white;
            font-weight: bold;
            text-decoration: none;
            border-radius: 6px;
            transition: background-color 0.3s ease;
        }

        .btn:hover {
            background-color: #303f9f;
        }

        .footer {
            text-align: center;
            font-size: 13px;
            color: #888;
            margin-top: 40px;
        }

        .info-block {
            font-size: 16px;
            line-height: 1.6;
            color: #444;
        }
        .btn {
            display: inline-block;
            margin: 10px 10px 10px 0;
            padding: 14px 20px;
            background-color: #3f51b5;
            color: white;
            font-weight: bold;
            text-decoration: none;
            border-radius: 6px;
            transition: background-color 0.3s ease;
        }

    </style>
</head>
<body>

<div class="header">
    <h1>Bienvenue sur l’Espace Administrateur</h1>
</div>

<div class="container">

    <div class="section">
        <h2>Mes informations</h2>
        <div class="info-block">
            <p><strong>Nom :</strong> <c:out value="${user.nom}" /></p>
            <p><strong>Email :</strong> <c:out value="${user.email}" /></p>
            <p><strong>Rôle :</strong> <c:out value="${user.role}" /></p>
        </div>
    </div>

    <div class="section">
        <h2>Actions rapides</h2>

        <a href="${pageContext.request.contextPath}/admin/users" class="btn">👥 Gérer les utilisateurs</a>
        <a href="${pageContext.request.contextPath}/credit/list" class="btn">📋 Voir les demandes de crédit</a>
        <a href="${pageContext.request.contextPath}/admin/compte/form" class="btn">🏦 Gérer les comptes clients</a>
        <a href="${pageContext.request.contextPath}/views/login.jsp" class="btn" style="background-color: #e53935;">🔒 Se déconnecter</a>
    </div>

</div>

<div class="footer">
    &copy; 2025 Bankati — Espace administrateur
</div>

</body>
</html>
