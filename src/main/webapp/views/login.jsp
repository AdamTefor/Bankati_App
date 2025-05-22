<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Connexion - Bankati</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        :root {
            --primary-color: #3949ab;
            --primary-hover: #303f9f;
            --secondary-color: #5c6bc0;
            --accent-color: #7986cb;
            --text-color: #333;
            --light-text: #666;
            --background: linear-gradient(135deg, #3949ab, #5c6bc0);
            --error-bg: #ffebee;
            --error-color: #c62828;
            --error-border: #ef9a9a;
        }

        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
            font-family: 'Segoe UI', 'Roboto', sans-serif;
        }

        body {
            background: var(--background);
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            padding: 20px;
        }

        .login-container {
            max-width: 420px;
            width: 100%;
        }

        .bank-logo {
            text-align: center;
            margin-bottom: 25px;
        }

        .bank-logo h1 {
            color: white;
            font-size: 36px;
            font-weight: 700;
            text-shadow: 0 2px 4px rgba(0,0,0,0.2);
            letter-spacing: 1px;
        }

        .bank-logo span {
            font-style: italic;
            font-weight: 300;
        }

        .login-box {
            background-color: white;
            border-radius: 15px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.25);
            overflow: hidden;
        }

        .login-header {
            background-color: var(--primary-color);
            color: white;
            padding: 25px 30px;
            text-align: center;
            position: relative;
        }

        .login-header h2 {
            font-size: 24px;
            font-weight: 600;
            margin: 0;
        }

        .login-form {
            padding: 30px;
        }

        .erreur {
            background-color: var(--error-bg);
            color: var(--error-color);
            padding: 12px 15px;
            margin-bottom: 20px;
            border: 1px solid var(--error-border);
            border-radius: 8px;
            font-size: 14px;
            display: flex;
            align-items: center;
        }

        .erreur i {
            margin-right: 10px;
            font-size: 16px;
        }

        .form-group {
            margin-bottom: 22px;
            position: relative;
        }

        .form-group label {
            display: block;
            color: var(--text-color);
            font-weight: 500;
            margin-bottom: 8px;
            font-size: 15px;
        }

        .input-with-icon {
            position: relative;
        }

        .input-with-icon input {
            width: 100%;
            padding: 14px 15px 14px 50px;
            border: 1px solid #ddd;
            border-radius: 8px;
            font-size: 15px;
            transition: all 0.3s ease;
        }

        .input-with-icon input:focus {
            border-color: var(--primary-color);
            box-shadow: 0 0 0 3px rgba(63, 81, 181, 0.15);
            outline: none;
        }

        .input-with-icon .icon {
            position: absolute;
            left: 18px;
            top: 50%;
            transform: translateY(-50%);
            color: var(--primary-color);
            font-size: 18px;
        }

        .btn {
            width: 100%;
            padding: 15px;
            background-color: var(--primary-color);
            color: white;
            border: none;
            border-radius: 8px;
            font-size: 16px;
            font-weight: 600;
            cursor: pointer;
            transition: all 0.3s ease;
            box-shadow: 0 3px 6px rgba(0,0,0,0.1);
            margin-top: 5px;
        }

        .btn:hover {
            background-color: var(--primary-hover);
            box-shadow: 0 5px 12px rgba(0,0,0,0.2);
            transform: translateY(-2px);
        }

        .btn:active {
            transform: translateY(0);
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }

        .options {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-top: 20px;
            margin-bottom: 25px;
            font-size: 14px;
        }

        .options a {
            color: var(--primary-color);
            text-decoration: none;
            transition: color 0.3s;
        }

        .options a:hover {
            color: var(--primary-hover);
            text-decoration: underline;
        }

        .remember-me {
            display: flex;
            align-items: center;
        }

        .remember-me input {
            margin-right: 8px;
        }

        .footer {
            text-align: center;
            color: rgba(255, 255, 255, 0.8);
            margin-top: 25px;
            font-size: 13px;
        }

        @media (max-width: 480px) {
            .login-form {
                padding: 20px;
            }

            .login-header {
                padding: 20px;
            }

            .bank-logo h1 {
                font-size: 30px;
            }
        }
    </style>
</head>
<body>

<div class="login-container">
    <div class="bank-logo">
        <h1>Bankati<span>.</span></h1>
    </div>

    <div class="login-box">
        <div class="login-header">
            <h2>Connectez-vous à votre compte</h2>
        </div>

        <div class="login-form">
            <c:if test="${not empty erreur}">
                <div class="erreur">
                    <i class="fas fa-exclamation-circle"></i>
                        ${erreur}
                </div>
            </c:if>

            <form action="${pageContext.request.contextPath}/login" method="post">
                <div class="form-group">
                    <label for="email">Adresse e-mail</label>
                    <div class="input-with-icon">
                        <i class="fas fa-envelope icon"></i>
                        <input type="email" name="email" id="email" required placeholder="exemple@email.com">
                    </div>
                </div>

                <div class="form-group">
                    <label for="motDePasse">Mot de passe</label>
                    <div class="input-with-icon">
                        <i class="fas fa-lock icon"></i>
                        <input type="password" name="motDePasse" id="motDePasse" required placeholder="Votre mot de passe">
                    </div>
                </div>

                <div class="options">
                    <div class="remember-me">
                        <input type="checkbox" id="remember" name="remember">
                        <label for="remember">Se souvenir de moi</label>
                    </div>

                </div>

                <button type="submit" class="btn">
                    Se connecter <i class="fas fa-arrow-right" style="margin-left: 8px;"></i>
                </button>
            </form>
        </div>
    </div>

    <div class="footer">
        &copy; 2025 Bankati – Tous droits réservés | Sécurité bancaire garantie - Adam Tefor - 4IIR12
    </div>
</div>

</body>
</html>